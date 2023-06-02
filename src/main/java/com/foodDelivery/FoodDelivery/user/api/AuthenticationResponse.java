package com.foodDelivery.FoodDelivery.user.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationResponse {
    private String email;
    private String accessToken;
    private String refreshToken;
}