package com.foodDelivery.FoodDelivery;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.cloudinary.SingletonManager;

@SpringBootApplication
public class FoodDeliveryApplication {


	public static void main(String[] args) {
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "dbrxse2ht",
				"api_key",  "511333872587789",
				"api_secret", "vHk6hQ9aexNP_Hfc2e5fVoWOKDE"));
		SingletonManager manager = new SingletonManager();
		manager.setCloudinary(cloudinary);
		manager.init();

		SpringApplication.run(FoodDeliveryApplication.class, args);
	}

}
