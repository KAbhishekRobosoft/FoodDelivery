package com.foodDelivery.FoodDelivery.restuarant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orderdetails")
@NoArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer orderId;
    private String orderDate;
    private String orderStatus;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartid",insertable= false,updatable = false)
    private Cart cart;

    private int cartid;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getCartid() {
        return cartid;
    }

    public void setCartid(int cartid) {
        this.cartid = cartid;
    }



    public OrderDetails(String orderDate, int cartid) {
        this.orderDate = orderDate;
        this.cartid = cartid;
    }

    public OrderDetails(Integer orderId, String orderDate, String orderStatus, int cartid) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.cartid = cartid;
    }
}
