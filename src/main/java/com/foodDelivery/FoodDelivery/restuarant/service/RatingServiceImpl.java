package com.foodDelivery.FoodDelivery.restuarant.service;

import com.foodDelivery.FoodDelivery.restuarant.entity.Rating;
import com.foodDelivery.FoodDelivery.restuarant.entity.Restuarant;
import com.foodDelivery.FoodDelivery.restuarant.exception.GlobalException;
import com.foodDelivery.FoodDelivery.restuarant.repository.RatingRepository;
import com.foodDelivery.FoodDelivery.restuarant.repository.RestuarantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingRepository ratingRepo;

    @Autowired
    RestuarantRepository restuarantRepository;

    @Override
    public Rating add(Rating rating) throws GlobalException {

        float sum= 0;

        List<Rating> ratings= ratingRepo.findByRestuarantId(rating.getRestuarantid());
        if(ratings.isEmpty()){
            throw new GlobalException("no");
        }
        else{
            for(int i=0;i<ratings.size();i++){
                sum += ratings.get(i).getRating();
            }
            sum= sum / (ratings.size());
            Restuarant restuarant= restuarantRepository.findById(rating.getRestuarantid()).get();
            restuarant.setRating(sum);
            restuarantRepository.save(restuarant);
            return ratingRepo.save(rating);
        }
    }
}
