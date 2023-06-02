package com.foodDelivery.FoodDelivery.restuarant.service;

import com.cloudinary.Singleton;
import com.cloudinary.utils.ObjectUtils;
import com.foodDelivery.FoodDelivery.restuarant.entity.Image;
import com.foodDelivery.FoodDelivery.restuarant.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;
import java.io.IOException;
import java.util.Map;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepo;

    private final Cloudinary cloudinary = Singleton.getCloudinary();

    public String saveImage(MultipartFile file, String name) throws IOException {
            try{
                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                String urlLink = uploadResult.get("url").toString();
                Image image= new Image();
                image.setName(name);
                image.setUrl(urlLink);
                imageRepo.save(image);
                return "Image saved in DB";
            }catch(Exception ex){
                throw new RuntimeException("Error occurred");
            }
    }

}
