package com.foodDelivery.FoodDelivery.restuarant.controller;

import com.foodDelivery.FoodDelivery.restuarant.entity.OrderDetails;
import com.foodDelivery.FoodDelivery.restuarant.service.OrderDetailsService;
import com.foodDelivery.FoodDelivery.user.Token;
import com.foodDelivery.FoodDelivery.user.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/orderDetails")
public class OrderDetailsController {

    @Autowired
    OrderDetailsService orderDetailsService;

    @Autowired
    TokenRepository tokenRepo;

    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestHeader("Authorization") String token, @RequestBody OrderDetails orderDetails){
        Optional<Token> presentToken= tokenRepo.findByToken(token);
        if(presentToken.isPresent()) {
            return ResponseEntity.ok(orderDetailsService.placeOrder(orderDetails));
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/admin/{orderId}/update")
    public ResponseEntity<?> changeStatus(@RequestHeader("Authorization") String token, @PathVariable Integer orderId){
        Optional<Token> presentToken= tokenRepo.findByToken(token);
        if(presentToken.isPresent()) {
            return ResponseEntity.ok(orderDetailsService.changeStatus(orderId));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
