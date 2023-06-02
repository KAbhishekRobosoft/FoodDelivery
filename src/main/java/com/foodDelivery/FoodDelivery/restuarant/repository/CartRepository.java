package com.foodDelivery.FoodDelivery.restuarant.repository;

import com.foodDelivery.FoodDelivery.restuarant.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByRestuarantidAndUserid(Integer restuarantId,Integer userId);
    List<Cart> findAllByRestuarantid(Integer restuarantId);
    List<Cart> findAllByUserid(Integer userId);

}
