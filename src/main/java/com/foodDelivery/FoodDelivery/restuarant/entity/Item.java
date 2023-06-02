package com.foodDelivery.FoodDelivery.restuarant.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Item{


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double cost;

    private String image;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String foodType;

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    @ManyToOne
    @JoinColumn(name = "restuarantid", insertable= false,updatable = false)
    private Restuarant restuarant;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private Integer restuarantid;

    public Integer getRestuarantid() {
        return restuarantid;
    }

    public void setRestuarantid(Integer restuarantid) {
        this.restuarantid = restuarantid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @JsonBackReference
    public Restuarant getRestuarant() {
        return restuarant;
    }

    public void setRestuarant(Restuarant restuarant) {
        this.restuarant = restuarant;
    }

    public Item(String name, Double cost, String category, Integer restuarantid,String foodType) {
        this.name = name;
        this.cost = cost;
        this.category = category;
        this.restuarantid = restuarantid;
        this.foodType= foodType;
    }

    public Item(Integer id, String name, Double cost, String image, String category, Integer restuarantid,String foodType) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.image = image;
        this.category = category;
        this.restuarantid = restuarantid;
        this.foodType= foodType;
    }

    public Item(Integer id, String name, Double cost,String category, Integer restuarantid,String foodType) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.category = category;
        this.restuarantid = restuarantid;
        this.foodType= foodType;
    }
}