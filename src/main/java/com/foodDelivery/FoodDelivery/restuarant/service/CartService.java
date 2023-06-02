package com.foodDelivery.FoodDelivery.restuarant.service;

import com.foodDelivery.FoodDelivery.restuarant.entity.Cart;
import com.foodDelivery.FoodDelivery.restuarant.exception.GlobalException;

import java.util.List;

public interface CartService {
    public Cart deleteCartById(Integer cartId) throws GlobalException;

    public String deleteAllOrderItems(Integer cartId) throws GlobalException;

    public Cart getCartById(Integer cartId) throws GlobalException;

    public List<Cart> getAllByRestuarantId(Integer restuarantId) throws GlobalException;

    public List<Cart> getAllRestuarantCart() throws GlobalException;

    public List<Cart> getAllByUserId(Integer userId) throws GlobalException;

}
