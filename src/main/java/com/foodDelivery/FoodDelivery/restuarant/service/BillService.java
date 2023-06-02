package com.foodDelivery.FoodDelivery.restuarant.service;

import com.foodDelivery.FoodDelivery.restuarant.entity.Bill;
import com.foodDelivery.FoodDelivery.restuarant.exception.GlobalException;

public interface BillService {
    public Bill addBill() throws GlobalException;
}
