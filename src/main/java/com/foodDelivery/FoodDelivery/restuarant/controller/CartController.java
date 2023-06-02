package com.foodDelivery.FoodDelivery.restuarant.controller;

import com.foodDelivery.FoodDelivery.restuarant.entity.Cart;
import com.foodDelivery.FoodDelivery.restuarant.exception.GlobalException;
import com.foodDelivery.FoodDelivery.restuarant.service.CartService;
import com.foodDelivery.FoodDelivery.user.Token;
import com.foodDelivery.FoodDelivery.user.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    TokenRepository tokenRepo;

    @DeleteMapping("/{cartId}/deleteById")
    public ResponseEntity<?> deleteCartById(@RequestHeader("Authorization") String token, @PathVariable Integer cartId) throws GlobalException{
        Optional<Token> presentToken= tokenRepo.findByToken(token);
        if(presentToken.isPresent()) {
            return ResponseEntity.ok(cartService.deleteCartById(cartId));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/{cartId}/deleteAllOrderItems")
    public ResponseEntity<?> deleteAllOrderItems(@RequestHeader("Authorization") String token,@PathVariable Integer cartId) throws GlobalException{
        Optional<Token> presentToken= tokenRepo.findByToken(token);
        if(presentToken.isPresent()) {
            return ResponseEntity.ok(cartService.deleteAllOrderItems(cartId));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/{cartId}/getById")
    public ResponseEntity<?> getCartById(@RequestHeader("Authorization") String token,@PathVariable Integer cartId) throws GlobalException{
        Optional<Token> presentToken= tokenRepo.findByToken(token);
        if(presentToken.isPresent()) {
            return ResponseEntity.ok(cartService.getCartById(cartId));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/admin/{restuarantId}/getAllByRestuarantId")
    public List<Cart> getAllByRestuarantId(@PathVariable Integer restuarantId) throws GlobalException{
        return cartService.getAllByRestuarantId(restuarantId);
    }

    @GetMapping("/admin/getAllRestuarantCart")
    public List<Cart> getAllRestuarantCart() throws GlobalException{
        return cartService.getAllRestuarantCart();
    }

    @GetMapping("/{userId}/getAllByUserId")
    public ResponseEntity<?> getAllByUserId(@RequestHeader("Authorization") String token,@PathVariable Integer userId) throws GlobalException{
        Optional<Token> presentToken= tokenRepo.findByToken(token);
        if(presentToken.isPresent()) {
            return ResponseEntity.ok(cartService.getAllByUserId(userId));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
