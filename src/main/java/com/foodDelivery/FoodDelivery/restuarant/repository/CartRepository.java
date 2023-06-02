package com.foodDelivery.FoodDelivery.restuarant.repository;

import com.foodDelivery.FoodDelivery.restuarant.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByRestuarantIdAndUserId(Integer restuarantId,Integer userId);
    List<Cart> findAllByRestuarantId(Integer restuarantId);
    List<Cart> findAllByUserId(Integer userId);

}
