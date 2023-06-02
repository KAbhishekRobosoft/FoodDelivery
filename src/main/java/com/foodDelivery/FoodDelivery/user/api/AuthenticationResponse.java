package com.foodDelivery.FoodDelivery.user.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String email;
    private String accessToken;
    private String refreshToken;
}