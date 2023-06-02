package com.foodDelivery.FoodDelivery.restuarant.service;

import com.foodDelivery.FoodDelivery.restuarant.entity.Cart;
import com.foodDelivery.FoodDelivery.restuarant.entity.OrderItem;
import com.foodDelivery.FoodDelivery.restuarant.exception.GlobalException;
import com.foodDelivery.FoodDelivery.restuarant.request.OrderItemRequest;
import org.junit.jupiter.api.Order;

public interface OrderItemService {
    public OrderItem addOrderItem(OrderItemRequest request) throws GlobalException;

    public OrderItem decreaseQuantity(OrderItem orderItem) throws GlobalException;


}
