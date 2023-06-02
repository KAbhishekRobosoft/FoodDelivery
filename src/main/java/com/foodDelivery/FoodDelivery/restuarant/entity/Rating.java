package com.foodDelivery.FoodDelivery.restuarant.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.foodDelivery.FoodDelivery.user.User;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "rating")
@NoArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private float rating;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restuarantid",insertable= false,updatable = false)
    private Restuarant restuarant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid",insertable= false,updatable = false)
    private User user;

    private int restuarantid;

    @JsonIgnore
    public Restuarant getRestuarant() {
        return restuarant;
    }

    public void setRestuarant(Restuarant restuarant) {
        this.restuarant = restuarant;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRestuarantid() {
        return restuarantid;
    }

    public void setRestuarantid(int restuarantid) {
        this.restuarantid = restuarantid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    private int userid;

    public Rating(float rating, int restuarantid, int userId) {
        this.rating = rating;
        this.restuarantid = restuarantid;
        this.userid = userId;
    }

    public Rating(int id, float rating, int restuarantid, int userId) {
        this.id = id;
        this.rating = rating;
        this.restuarantid = restuarantid;
        this.userid = userId;
    }
}
