package com.foodDelivery.FoodDelivery.restuarant.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.foodDelivery.FoodDelivery.user.User;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @JsonManagedReference
    public List<OrderItem> getOrderItemList() {
        return OrderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        OrderItemList = orderItemList;
    }

    @Column(nullable = false)
    private int userid;

    @JsonManagedReference
    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    @OneToOne(mappedBy = "cart",cascade = CascadeType.ALL)
    private  Bill bill;

    @Column(nullable = false)
    private int restuarantid;

    @OneToMany(mappedBy ="cart",cascade = CascadeType.ALL)
    private List<OrderItem> OrderItemList = new ArrayList<>();

    public Cart(int userid,int restuarantid) {
        this.userid = userid;
        this.restuarantid= restuarantid;
    }

    public Integer getId() {
        return id;
    }

    public void setCartId(Integer id) {
        this.id = id;
    }

    public int getRestuarantid() {
        return restuarantid;
    }

    public void setRestuarantid(int restuarantid) {
        this.restuarantid = restuarantid;
    }

    public Cart(Integer id, int userid, int restuarantid) {
        this.id = id;
        this.userid = userid;
        this.restuarantid= restuarantid;
    }
}
