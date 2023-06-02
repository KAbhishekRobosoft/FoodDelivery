package com.foodDelivery.FoodDelivery.restuarant.service;

import com.foodDelivery.FoodDelivery.restuarant.entity.Restuarant;
import com.foodDelivery.FoodDelivery.restuarant.entity.RestuarantPage;
import com.foodDelivery.FoodDelivery.restuarant.entity.RestuarantSearchCriteria;
import com.foodDelivery.FoodDelivery.restuarant.exception.GlobalException;
import com.foodDelivery.FoodDelivery.restuarant.repository.ImageRepository;
import com.foodDelivery.FoodDelivery.restuarant.repository.RatingRepository;
import com.foodDelivery.FoodDelivery.restuarant.repository.RestuarantCriteriaRepository;
import com.foodDelivery.FoodDelivery.restuarant.repository.RestuarantRepository;
import com.foodDelivery.FoodDelivery.restuarant.request.RestuarantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Service
public class RestuarantServiceImpl implements RestuarantService {

    @Autowired
    RestuarantRepository restuarantRepo;

    @Autowired
    ImageRepository imageRepo;

    @Autowired
    RatingRepository ratingRepo;



    public List<Restuarant> getAllRestuarants(String keyword) throws GlobalException {
        if (keyword != null) {
            return restuarantRepo.search(keyword);
        }
        return restuarantRepo.findAll();
    }



    public Optional<Restuarant> getRestuarantById(Integer id) throws GlobalException {
        Optional<Restuarant> restuarant= restuarantRepo.findById(id);
        if(restuarant.isPresent()){
            return restuarantRepo.findById(id);
        }
        throw new GlobalException("No data exists for id"+" "+id);
    }


    public Restuarant AddRestuarant(Restuarant request) throws GlobalException {
        Optional<Restuarant> restuarant= restuarantRepo.findByName(request.getName());
        if(restuarant.isPresent()) {
            throw new GlobalException("Restuarant already Exists");
        }
        else{
            request.setImage(imageRepo.findByName(request.getName()).get().getUrl());
            return restuarantRepo.save(request);
        }

    }

    public Restuarant deleteRestuarantById(Integer id) throws GlobalException {
        Optional<Restuarant> restuarant= restuarantRepo.findById(id);
        if(restuarant.isPresent()){
            restuarantRepo.deleteById(id);
            return restuarant.get();
        }
        throw new GlobalException("Cannot delete non-existing data");
    }

    public Restuarant update(Restuarant restuarant) throws GlobalException {
        Optional<Restuarant> restuarant1= restuarantRepo.findById(restuarant.getId());
        if(restuarant1.isPresent()){
            return restuarantRepo.save(restuarant);
        }
        else
            throw new GlobalException("Cannot update non-existing data");
    }


}
