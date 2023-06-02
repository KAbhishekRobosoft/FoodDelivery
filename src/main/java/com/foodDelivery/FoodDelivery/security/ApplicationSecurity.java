package com.foodDelivery.FoodDelivery.security;
import com.foodDelivery.FoodDelivery.jwt.JwtTokenFilter;
import com.foodDelivery.FoodDelivery.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User "+username+" not found"))
        );
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    ex.getMessage());
        });
        http.authorizeRequests().antMatchers("/login","/register","/refreshToken","/restuarant/admin/*","/restuarant/admin/**","/item/admin/*","/image/**","/orderDetails/admin/**","/cart/admin/*","/cart/admin/**").permitAll()
                .anyRequest().authenticated();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
