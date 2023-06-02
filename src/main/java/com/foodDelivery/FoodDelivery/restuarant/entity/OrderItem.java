package com.foodDelivery.FoodDelivery.restuarant.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name="orderitems")
@NoArgsConstructor
public class OrderItem {
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

    @ManyToOne
    @JoinColumn(name = "cartid", insertable= false,updatable = false)
    private Cart cart;

    @Column(nullable = false)
    private int cartid;

    public int getCartid() {
        return cartid;
    }

    public void setCartid(int cartid) {
        this.cartid = cartid;
    }

    @JsonBackReference
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Column(nullable = false)
    private String foodType;

    @Column(nullable = false)
    private int quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public OrderItem(String name, Double cost, String category,String foodType,int cartid,int quantity) {
        this.name = name;
        this.cost = cost;
        this.category = category;
        this.foodType= foodType;
        this.cartid= cartid;
        this.quantity= quantity;
    }

    public OrderItem(Integer id, String name, Double cost, String image, String category,String foodType,int quantity,int cartid) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.image = image;
        this.category = category;
        this.foodType= foodType;
        this.quantity= quantity;
        this.cartid= cartid;
    }
}
