package com.foodDelivery.FoodDelivery.restuarant.otp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OTPGetResponse {
    private int otp;
    private String email;
}
