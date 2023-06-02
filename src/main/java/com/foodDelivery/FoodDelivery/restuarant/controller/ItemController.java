package com.foodDelivery.FoodDelivery.restuarant.controller;
import com.foodDelivery.FoodDelivery.restuarant.entity.Item;
import com.foodDelivery.FoodDelivery.restuarant.exception.GlobalException;
import com.foodDelivery.FoodDelivery.restuarant.service.ItemService;
import com.foodDelivery.FoodDelivery.user.Token;
import com.foodDelivery.FoodDelivery.user.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    TokenRepository tokenRepo;


    @GetMapping("/admin/getAll")
    public List<Item> findAllItems() throws GlobalException {
        return itemService.findAllItems();
    }

    @GetMapping("/{id}/get")
    public ResponseEntity<?> findItemById(@RequestHeader("Authorization") String token, @PathVariable Integer id) throws GlobalException{
        Optional<Token> presentToken= tokenRepo.findByToken(token);
        if(presentToken.isPresent()) {
            return ResponseEntity.ok(itemService.findItemById(id));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/admin/add")
    public Item AddItem(@RequestBody Item item) throws GlobalException{
        return itemService.AddItem(item);
    }

    @PutMapping("/admin/update")
    public Item update(@RequestBody Item item) throws GlobalException{
        return itemService.update(item);
    }

    @DeleteMapping("/admin/{id}/delete")
    public Item deleteItemById(@PathVariable Integer id) throws GlobalException{
        return itemService.deleteItemById(id);
    }
}