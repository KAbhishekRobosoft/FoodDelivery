package com.foodDelivery.FoodDelivery.user.api;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
  private int id;
  private String email;
}
