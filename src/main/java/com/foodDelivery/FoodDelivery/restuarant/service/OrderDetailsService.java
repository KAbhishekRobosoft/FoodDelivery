package com.foodDelivery.FoodDelivery.restuarant.service;

import com.foodDelivery.FoodDelivery.restuarant.entity.OrderDetails;
import com.foodDelivery.FoodDelivery.restuarant.exception.GlobalException;
import org.junit.jupiter.api.Order;

public interface OrderDetailsService {
    public OrderDetails placeOrder(OrderDetails orderDetails) throws GlobalException;

    public String changeStatus(Integer orderId);

    public OrderDetails getOrderDetail(Integer orderDetailId) throws GlobalException;
}
