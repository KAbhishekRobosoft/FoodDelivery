package com.foodDelivery.FoodDelivery.restuarant.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String area;
    private String city;
    private String state;
    private String country;
    private String pincode;
}