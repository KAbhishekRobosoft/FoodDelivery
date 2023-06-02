package com.foodDelivery.FoodDelivery.restuarant.repository;

import com.foodDelivery.FoodDelivery.restuarant.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
    List<Item> findByRestuarantId(Integer id);
}
