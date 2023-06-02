package com.foodDelivery.FoodDelivery.user.refreshToken;

import javax.validation.constraints.NotBlank;

public class TokenRefreshRequest {
    @NotBlank
    private String refreshToken;


    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public TokenRefreshRequest(String refreshToken, int userId) {
        this.refreshToken = refreshToken;

    }
}
