package com.foodDelivery.FoodDelivery.user.api;

import com.foodDelivery.FoodDelivery.jwt.JwtTokenUtil;
import com.foodDelivery.FoodDelivery.restuarant.otp.OTPService;
import com.foodDelivery.FoodDelivery.user.Token;
import com.foodDelivery.FoodDelivery.user.TokenRepository;
import com.foodDelivery.FoodDelivery.user.User;
import com.foodDelivery.FoodDelivery.user.UserRepository;
import com.foodDelivery.FoodDelivery.user.refreshToken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class AuthApi {

    @Autowired
    JwtTokenUtil jwtUtil;

    @Autowired
    RefreshTokenRepository refreshRepo;

    @Autowired
    AuthenticationManager authManager;
    @Autowired
    UserRepository repo;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    OTPService otpService;

    @Autowired
    TokenRepository tokenRepo;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationRequest request) {

        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            Optional<User> userData= repo.findByEmail(request.getEmail());
            Optional<Token> presentToken= tokenRepo.findByUserId(userData.get().getId());
            if(presentToken.isPresent()){
                presentToken.get().setToken(accessToken);
                tokenRepo.save(presentToken.get());
            }
            else{
                Token newToken= new Token();
                newToken.setToken(accessToken);
            }
            String refreshToken = refreshTokenService.createRefreshToken(user.getId());
            AuthenticationResponse response = new AuthenticationResponse(user.getEmail(),accessToken,refreshToken);

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Optional<User> userInfo= repo.findByEmail(user.getEmail());
        try {
            if(userInfo.isPresent())
                throw new RuntimeException("User already registered");
            else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                User userData=  repo.save(user);
                RegisterRequest response = new RegisterRequest();
                response.setId(userData.getId());
                response.setEmail(userData.getEmail());
                return ResponseEntity.ok().body(response);
            }

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest request){
        String requestRefreshToken = request.getRefreshToken();
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String refreshToken = jwtUtil.generateAccessToken(user);
                    Optional<Token> presentToken= tokenRepo.findByUserId(user.getId());
                    presentToken.ifPresent(token -> {
                        presentToken.get().setToken(refreshToken);
                        tokenRepo.save(presentToken.get());
                    });

                    return ResponseEntity.ok(new TokenRefreshResponse(refreshToken,requestRefreshToken));
                })
                .orElseThrow(() -> new RuntimeException("Token not present in database"));
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestHeader("Authorization") String token,@RequestBody ResetPasswordRequest request) {
        Optional<Token> getToken= tokenRepo.findByToken(token);
        Optional<User> userInfo = repo.findByEmail(request.getEmail());

        if(getToken.isPresent()) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (userInfo.isPresent()) {
                userInfo.get().setPassword(passwordEncoder.encode(request.getNewPassword()));
                User userData = repo.save(userInfo.get());
                ResetPasswordResponse response = new ResetPasswordResponse("success");
                return ResponseEntity.ok(response);
            } else {
                throw new RuntimeException("User does not exist");
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token,@RequestParam("email") String email) {
        Optional<User> userInfo = repo.findByEmail(email);
        Optional<Token> presentToken= tokenRepo.findByToken(token);
        if(presentToken.isPresent()) {
            if (userInfo.isPresent()) {
                tokenRepo.deleteByToken(token);
                refreshRepo.deleteAllByUserId(userInfo.get().getId());
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                throw new RuntimeException("User does not exist");
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

}
