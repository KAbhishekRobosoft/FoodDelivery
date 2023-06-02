package com.foodDelivery.FoodDelivery.restuarant.service;


import com.foodDelivery.FoodDelivery.restuarant.entity.OrderDetails;
import com.foodDelivery.FoodDelivery.restuarant.exception.GlobalException;
import com.foodDelivery.FoodDelivery.restuarant.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Autowired
    OrderDetailsRepository orderDetailsRepo;

    @Override
    public OrderDetails placeOrder(OrderDetails orderDetails) throws GlobalException {
        Optional<OrderDetails> orderDetailsInfo = orderDetailsRepo.findByCartid(orderDetails.getCartid());
        if(orderDetailsInfo.isPresent() && Objects.equals(orderDetailsInfo.get().getOrderStatus(), "active")){
            throw new GlobalException("Order already present");
        }
        if(orderDetailsInfo.isPresent()) {
            orderDetails.setOrderDate(LocalDateTime.now().toString());
            orderDetails.setOrderStatus("active");
            return orderDetailsRepo.save(orderDetails);
        }

        throw new GlobalException("Enter proper cart id");
    }

    @Override
    public String changeStatus(Integer orderId) {
        Optional<OrderDetails> orderDetails= orderDetailsRepo.findById(orderId);
        if(orderDetails.isPresent() && Objects.equals(orderDetails.get().getOrderStatus(), "delivered")){
            return "Order for given id does not exist";
        }
        orderDetails.get().setOrderStatus("delivered");
        orderDetailsRepo.save(orderDetails.get());
        return "Order status changed";
    }

    @Override
    public OrderDetails getOrderDetail(Integer orderId) throws GlobalException {
        Optional<OrderDetails> orderDetails= orderDetailsRepo.findById(orderId);
        if(orderDetails.isPresent()){
            return orderDetails.get();
        }
        throw new GlobalException("No data present");
    }
}
