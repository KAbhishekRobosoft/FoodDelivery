package com.foodDelivery.FoodDelivery.restuarant.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest {
    private int userId;
    private int restuarantId;
    private String name;
    private double cost;
    private String category;
    private String foodType;
    private int quantity;

}
