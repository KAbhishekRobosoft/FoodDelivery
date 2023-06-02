package com.foodDelivery.FoodDelivery.restuarant.service;

import com.foodDelivery.FoodDelivery.restuarant.entity.Rating;
import com.foodDelivery.FoodDelivery.restuarant.exception.GlobalException;


public interface RatingService {
        public Rating add(Rating rating) throws GlobalException;

}
