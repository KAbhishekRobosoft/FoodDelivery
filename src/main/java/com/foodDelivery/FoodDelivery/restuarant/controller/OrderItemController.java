package com.foodDelivery.FoodDelivery.restuarant.controller;


import com.foodDelivery.FoodDelivery.restuarant.entity.OrderItem;
import com.foodDelivery.FoodDelivery.restuarant.exception.GlobalException;
import com.foodDelivery.FoodDelivery.restuarant.request.OrderItemRequest;
import com.foodDelivery.FoodDelivery.restuarant.service.OrderItemService;
import com.foodDelivery.FoodDelivery.user.Token;
import com.foodDelivery.FoodDelivery.user.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {

    @Autowired
    TokenRepository tokenRepo;

    @Autowired
    OrderItemService orderItemService;

    @PostMapping("/add")
    public ResponseEntity<?> addOrderItem(@RequestHeader("Authorization") String token, @RequestBody @Valid OrderItemRequest request) throws GlobalException{
        Optional<Token> presentToken= tokenRepo.findByToken(token);
        if(presentToken.isPresent()) {
            return ResponseEntity.ok(orderItemService.addOrderItem(request));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/decreaseQuantity")
    public ResponseEntity<?> decreaseQuantity(@RequestHeader("Authorization") String token,@RequestBody @Valid OrderItem orderItem) throws GlobalException{
        Optional<Token> presentToken= tokenRepo.findByToken(token);
        if(presentToken.isPresent()) {
            return ResponseEntity.ok(orderItemService.decreaseQuantity(orderItem));
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
