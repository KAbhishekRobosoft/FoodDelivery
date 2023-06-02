package com.foodDelivery.FoodDelivery.restuarant.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestuarantRequest {

        @NotNull
        private String name;
        @NotNull
        private String city;
        @NotNull
        private String state;
        @NotNull
        private String country;

}
