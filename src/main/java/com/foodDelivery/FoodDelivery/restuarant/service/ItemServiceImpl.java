package com.foodDelivery.FoodDelivery.restuarant.service;

import com.foodDelivery.FoodDelivery.restuarant.entity.Image;
import com.foodDelivery.FoodDelivery.restuarant.entity.Item;
import com.foodDelivery.FoodDelivery.restuarant.exception.GlobalException;
import com.foodDelivery.FoodDelivery.restuarant.repository.ImageRepository;
import com.foodDelivery.FoodDelivery.restuarant.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    ItemRepository itemRepo;

    @Autowired
    ImageRepository imageRepo;



    public List<Item> findAllItems() throws GlobalException {
        List<Item> items= itemRepo.findAll();
        if(items.isEmpty())
            throw new GlobalException("No data present in items");

        return items;
    }

    public Item findItemById(Integer id) throws GlobalException {
        Optional<Item> item= itemRepo.findById(id);
        if(item.isPresent()){
            return item.get();
        }
        throw new GlobalException("No data for given id"+" "+id);
    }


    public Item AddItem(Item item) throws GlobalException {
            Optional<Image> img= imageRepo.findByName(item.getName());
        if(img.isPresent()) {
            item.setImage(imageRepo.findByName(item.getName()).get().getUrl());
            return itemRepo.save(item);
        }
            throw new GlobalException("Enter data in proper format");
    }

    public Item update(Item item) throws GlobalException{
        Optional<Item> item1= itemRepo.findById(item.getId());
        if(item1.isPresent()){
            item.setImage(imageRepo.findByName(item.getName()).get().getUrl());
            itemRepo.save(item);
        }
        throw new GlobalException("Cannot update non-existing data");
    }

    public Item deleteItemById(Integer id) throws GlobalException{
        Optional<Item> item= itemRepo.findById(id);
        if(item.isPresent()){
            itemRepo.deleteById(id);
            return item.get();
        }
        throw new GlobalException("Cannot delete non-existing data");
    }
}

