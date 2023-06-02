package com.foodDelivery.FoodDelivery.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="tokens")
@NoArgsConstructor
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String token;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid",insertable= false,updatable = false)
    private User user;

    private int userid;


    public int getId() {
        return id;
    }

    public Token(String token, int userid, String status) {
        this.token = token;
        this.userid = userid;
    }

    public Token(int id, String token, int userid, String status) {
        this.id = id;
        this.token = token;
        this.userid = userid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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

}
