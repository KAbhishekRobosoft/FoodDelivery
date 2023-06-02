package com.foodDelivery.FoodDelivery.restuarant.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bill")
@NoArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String billdate;
    private Double totalcost;


   @JsonBackReference
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

    private Integer totalitem;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartid", insertable= false,updatable = false)
    private Cart cart;

    private int cartid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBilldate() {
        return billdate;
    }

    public void setBilldate(String billdate) {
        this.billdate = billdate;
    }

    public Double getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(Double totalcost) {
        this.totalcost = totalcost;
    }

    public Integer getTotalitem() {
        return totalitem;
    }

    public void setTotalitem(Integer totalitem) {
        this.totalitem = totalitem;
    }

    public Bill(Integer id, String billdate, Double totalcost, Integer totalitem, int cartid) {
        this.id = id;
        this.billdate = billdate;
        this.totalcost = totalcost;
        this.totalitem = totalitem;
        this.cartid = cartid;
    }

    public Bill(String billdate, Double totalcost, Integer totalitem, int cartid) {
        this.billdate = billdate;
        this.totalcost = totalcost;
        this.totalitem = totalitem;
        this.cartid = cartid;
    }
}
