package com.foodDelivery.FoodDelivery.restuarant.controller;

import com.foodDelivery.FoodDelivery.restuarant.entity.Rating;
import com.foodDelivery.FoodDelivery.restuarant.entity.Restuarant;
import com.foodDelivery.FoodDelivery.restuarant.exception.GlobalException;
import com.foodDelivery.FoodDelivery.restuarant.service.RatingService;
import com.foodDelivery.FoodDelivery.user.Token;
import com.foodDelivery.FoodDelivery.user.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @Autowired
    TokenRepository tokenRepo;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestHeader("Authorization") String token, @RequestBody @Valid Rating rating) throws GlobalException {
        Optional<Token> presentToken= tokenRepo.findByToken(token);
        if(presentToken.isPresent()) {
            return ResponseEntity.ok(ratingService.add(rating));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
