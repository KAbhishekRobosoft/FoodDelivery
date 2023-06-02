package com.foodDelivery.FoodDelivery.restuarant.controller;

import com.foodDelivery.FoodDelivery.restuarant.entity.Item;
import com.foodDelivery.FoodDelivery.restuarant.entity.Restuarant;
import com.foodDelivery.FoodDelivery.restuarant.entity.RestuarantPage;
import com.foodDelivery.FoodDelivery.restuarant.entity.RestuarantSearchCriteria;
import com.foodDelivery.FoodDelivery.restuarant.exception.GlobalException;
import com.foodDelivery.FoodDelivery.restuarant.service.RestuarantCriteriaService;
import com.foodDelivery.FoodDelivery.restuarant.service.RestuarantService;
import com.foodDelivery.FoodDelivery.user.Token;
import com.foodDelivery.FoodDelivery.user.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/restuarant")
public class RestuarantController {
    @Autowired
    RestuarantService restService;

    @Autowired
    TokenRepository tokenRepo;

    private final RestuarantCriteriaService restuarantCriteriaService;

    public RestuarantController(RestuarantCriteriaService restuarantCriteriaService){
        this.restuarantCriteriaService= restuarantCriteriaService;
    }


    @GetMapping("/admin/{id}/items")
    public List<Item> getItemsByRestuarantId(@PathVariable Integer id) throws GlobalException {
        Optional<Restuarant> restuarant = restService.getRestuarantById(id);
        if(restuarant.isPresent()) {
            return restuarant.get().getItemList();
        }
        throw new GlobalException("No Items available for a given restuarant name"+" "+restuarant.get().getName());
    }

    @PostMapping("/admin/addNew")
    public Restuarant AddRestuarant(@RequestBody Restuarant restuarant) throws GlobalException {
        return restService.AddRestuarant(restuarant);
    }

    @GetMapping("{id}/get")
    public ResponseEntity<?> getRestuarantById(@RequestHeader("Authorization") String token, @PathVariable Integer id) throws GlobalException {
        Optional<Token> presentToken= tokenRepo.findByToken(token);
        if(presentToken.isPresent()) {
            return ResponseEntity.ok(restService.getRestuarantById(id).get());
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/filterBy/{keyword}")
    public ResponseEntity<?> getAllRestuarants(@RequestHeader("Authorization") String token,@PathVariable String keyword) throws GlobalException {
        Optional<Token> presentToken= tokenRepo.findByToken(token);
        if(presentToken.isPresent()) {
            return ResponseEntity.ok(restService.getAllRestuarants(keyword));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/filterByParameters")
    public ResponseEntity<?> getAllRestuarantsByParameters(@RequestHeader("Authorization") String token,RestuarantPage restuarantPage, RestuarantSearchCriteria restuarantSearchCriteria) throws GlobalException{
        Optional<Token> presentToken= tokenRepo.findByToken(token);
        if(presentToken.isPresent()) {
            return ResponseEntity.ok(restuarantCriteriaService.getAllRestuarantsByParameters(restuarantPage, restuarantSearchCriteria));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/admin/{id}/delete")
    public Restuarant deleteRestuarantById(Integer id) throws GlobalException {
        return restService.deleteRestuarantById(id);
    }

    @PutMapping("/admin/update")
    public Restuarant update(Restuarant restuarant) throws GlobalException {
        return restService.update(restuarant);
    }
}
