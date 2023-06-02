package com.foodDelivery.FoodDelivery.restuarant.service;

import com.foodDelivery.FoodDelivery.restuarant.entity.Restuarant;
import com.foodDelivery.FoodDelivery.restuarant.entity.RestuarantPage;
import com.foodDelivery.FoodDelivery.restuarant.entity.RestuarantSearchCriteria;
import com.foodDelivery.FoodDelivery.restuarant.exception.GlobalException;
import com.foodDelivery.FoodDelivery.restuarant.request.RestuarantRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface RestuarantService {

    public List<Restuarant> getAllRestuarants(String keyword) throws GlobalException;

    public Optional<Restuarant> getRestuarantById(Integer id) throws GlobalException;

    public Restuarant AddRestuarant(Restuarant request) throws GlobalException;

    public Restuarant deleteRestuarantById(Integer id) throws GlobalException;

    public Restuarant update(Restuarant restuarant) throws GlobalException;



}
