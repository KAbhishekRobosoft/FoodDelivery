package com.foodDelivery.FoodDelivery.restuarant.service;

import com.foodDelivery.FoodDelivery.restuarant.entity.OrderDetails;

public interface OrderDetailsService {
    public String placeOrder(OrderDetails orderDetails);

    public String changeStatus(Integer orderId);
}
