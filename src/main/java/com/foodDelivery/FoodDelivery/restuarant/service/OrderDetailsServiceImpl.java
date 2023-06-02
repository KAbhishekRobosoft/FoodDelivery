package com.foodDelivery.FoodDelivery.restuarant.service;


import com.foodDelivery.FoodDelivery.restuarant.entity.OrderDetails;
import com.foodDelivery.FoodDelivery.restuarant.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Autowired
    OrderDetailsRepository orderDetailsRepo;

    @Override
    public String placeOrder(OrderDetails orderDetails) {
        Optional<OrderDetails> orderDetailsInfo = orderDetailsRepo.findByCartId(orderDetails.getCartid());
        if(orderDetailsInfo.isPresent()){
            return "Order already placed";
        }
        orderDetails.setOrderStatus("active");
        orderDetailsRepo.save(orderDetails);
        return "Order placed";
    }

    @Override
    public String changeStatus(Integer orderId) {
        Optional<OrderDetails> orderDetails= orderDetailsRepo.findById(orderId);
        if(orderDetails.isPresent()){
            return "Order for given id does not exist";
        }
        orderDetails.get().setOrderStatus("delivered");
        orderDetailsRepo.save(orderDetails.get());
        return "Order status changed";
    }
}
