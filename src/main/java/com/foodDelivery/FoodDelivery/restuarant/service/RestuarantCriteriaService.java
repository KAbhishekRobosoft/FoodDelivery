package com.foodDelivery.FoodDelivery.restuarant.service;

import com.foodDelivery.FoodDelivery.restuarant.entity.Restuarant;
import com.foodDelivery.FoodDelivery.restuarant.entity.RestuarantPage;
import com.foodDelivery.FoodDelivery.restuarant.entity.RestuarantSearchCriteria;
import com.foodDelivery.FoodDelivery.restuarant.exception.GlobalException;
import com.foodDelivery.FoodDelivery.restuarant.repository.RestuarantCriteriaRepository;
import com.foodDelivery.FoodDelivery.restuarant.repository.RestuarantRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class RestuarantCriteriaService {
    private final RestuarantRepository restuarantRepository;
    private final RestuarantCriteriaRepository restuarantCriteriaRepository;

    public RestuarantCriteriaService(RestuarantRepository restuarantRepository,RestuarantCriteriaRepository restuarantCriteriaRepository){
        this.restuarantRepository= restuarantRepository;
        this.restuarantCriteriaRepository= restuarantCriteriaRepository;
    }

    public Page<Restuarant> getAllRestuarantsByParameters(RestuarantPage restuarantPage, RestuarantSearchCriteria restuarantSearchCriteria) throws GlobalException {
        return restuarantCriteriaRepository.findAllWithFilters(restuarantPage,restuarantSearchCriteria);
    }

}
