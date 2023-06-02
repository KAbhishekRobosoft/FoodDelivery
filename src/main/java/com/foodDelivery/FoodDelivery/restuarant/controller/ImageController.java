package com.foodDelivery.FoodDelivery.restuarant.controller;


import com.foodDelivery.FoodDelivery.restuarant.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping("/admin/save")
    public void saveImage(@RequestParam("file") MultipartFile file,@RequestParam("name") String name) throws IOException {
         imageService.saveImage(file,name);
    }


}
