package com.foodDelivery.FoodDelivery.restuarant.repository;

import com.foodDelivery.FoodDelivery.restuarant.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill,Integer> {

    Optional<Bill> findByCartId(Integer id);
    public void deleteByCartId(Integer id);
}
