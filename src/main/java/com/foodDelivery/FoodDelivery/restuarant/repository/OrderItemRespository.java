package com.foodDelivery.FoodDelivery.restuarant.repository;

import com.foodDelivery.FoodDelivery.restuarant.entity.OrderItem;
import org.junit.jupiter.api.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemRespository extends JpaRepository<OrderItem,Integer> {
    Optional<OrderItem> findByCartId(Integer cartId);
    List<OrderItem> findAllByCartId(Integer cartId);
    public void deleteAllByCartid(Integer cartId);
}
