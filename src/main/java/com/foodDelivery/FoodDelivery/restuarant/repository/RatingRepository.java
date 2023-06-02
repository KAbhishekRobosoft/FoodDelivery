package com.foodDelivery.FoodDelivery.restuarant.repository;
import com.foodDelivery.FoodDelivery.restuarant.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer> {
    List<Rating> findByRestuarantId(Integer restuarantId);
}
