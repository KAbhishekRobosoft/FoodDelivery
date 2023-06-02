package com.foodDelivery.FoodDelivery.restuarant.service;

import com.foodDelivery.FoodDelivery.restuarant.entity.Item;
import com.foodDelivery.FoodDelivery.restuarant.exception.GlobalException;

import java.util.List;
import java.util.Optional;

public interface ItemService {


        public List<Item> findAllItems() throws GlobalException;

        public Item findItemById(Integer id) throws GlobalException;

        public Item AddItem(Item item) throws GlobalException;

        public Item update(Item item) throws GlobalException;

        public Item deleteItemById(Integer id) throws GlobalException;
}
