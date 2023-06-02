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

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid",insertable= false,updatable = false)
    private User user;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restuarantid",insertable= false,updatable = false)
    private Restuarant restuarant;

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

    @JsonIgnore
    public Restuarant getRestuarant() {
        return restuarant;
    }

    public void setRestuarant(Restuarant restuarant) {
        this.restuarant = restuarant;
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
