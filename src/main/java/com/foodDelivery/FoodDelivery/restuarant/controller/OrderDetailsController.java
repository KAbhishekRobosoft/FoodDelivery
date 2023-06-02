package com.foodDelivery.FoodDelivery.restuarant.controller;

import com.foodDelivery.FoodDelivery.restuarant.entity.OrderDetails;
import com.foodDelivery.FoodDelivery.restuarant.exception.GlobalException;
import com.foodDelivery.FoodDelivery.restuarant.repository.OrderDetailsRepository;
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
    OrderDetailsRepository orderDetailRepo;

    @Autowired
    TokenRepository tokenRepo;

    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestHeader("Authorization") String token, @RequestBody OrderDetails orderDetails) throws GlobalException {
        token = token.split(" ")[1];
        Optional<Token> presentToken= tokenRepo.findByToken(token);
        if(presentToken.isPresent()) {
            return ResponseEntity.ok(orderDetailsService.placeOrder(orderDetails));
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/admin/{orderId}/update")
    public ResponseEntity<?> changeStatus(@PathVariable Integer orderId){
            return ResponseEntity.ok(orderDetailsService.changeStatus(orderId));
    }

    @GetMapping("/{orderId}/getOrderDetails")
    public ResponseEntity<?> getOrderDetail(@RequestHeader("Authorization") String token,@PathVariable Integer orderId) throws GlobalException {
        token = token.split(" ")[1];
        Optional<Token> presentToken= tokenRepo.findByToken(token);
        if(presentToken.isPresent()) {
            return ResponseEntity.ok(orderDetailsService.getOrderDetail(orderId));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
