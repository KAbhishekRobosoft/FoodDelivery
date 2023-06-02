package com.foodDelivery.FoodDelivery.restuarant.service;

import com.foodDelivery.FoodDelivery.restuarant.entity.Bill;
import com.foodDelivery.FoodDelivery.restuarant.entity.Cart;
import com.foodDelivery.FoodDelivery.restuarant.entity.OrderItem;
import com.foodDelivery.FoodDelivery.restuarant.exception.GlobalException;
import com.foodDelivery.FoodDelivery.restuarant.repository.BillRepository;
import com.foodDelivery.FoodDelivery.restuarant.repository.CartRepository;
import com.foodDelivery.FoodDelivery.restuarant.repository.ImageRepository;
import com.foodDelivery.FoodDelivery.restuarant.repository.OrderItemRespository;
import com.foodDelivery.FoodDelivery.restuarant.request.OrderItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemRespository orderItemRepo;

    @Autowired
    CartRepository cartRepo;

    @Autowired
    ImageRepository imageRepo;

    @Autowired
    BillRepository billRepo;

    @Override
    public OrderItem addOrderItem(OrderItemRequest request) throws GlobalException {
        Optional<Cart> cart = cartRepo.findByRestuarantIdAndUserId(request.getRestuarantId(),request.getUserId());

        String url = imageRepo.findByName(request.getName()).get().getUrl();

        OrderItem orderItem = new OrderItem();
        orderItem.setCategory(request.getCategory());
        orderItem.setCost(request.getCost());
        orderItem.setImage(url);
        orderItem.setFoodType(request.getFoodType());
        orderItem.setName(request.getName());
        orderItem.setQuantity(request.getQuantity());

        if (cart.isPresent()) {
            orderItem.setCartid(cart.get().getId());
            orderItemRepo.save(orderItem);
            Optional<Bill> bill= billRepo.findByCartId(cart.get().getId());
            bill.get().setBilldate(LocalDateTime.now().toString());
            bill.get().setTotalitem(bill.get().getTotalitem() + request.getQuantity());
            bill.get().setTotalcost(bill.get().getTotalcost() + (request.getCost() * request.getQuantity()));
            billRepo.save(bill.get());
            return orderItem;
        }
        else {
            Cart newCart = new Cart();
            newCart.setRestuarantid(request.getRestuarantId());
            newCart.setUserid(request.getUserId());
            Cart savedCart = cartRepo.save(newCart);
            orderItem.setCartid(savedCart.getId());
            orderItemRepo.save(orderItem);
            Bill bill= new Bill();
            bill.setBilldate(LocalDateTime.now().toString());
            bill.setCartid(savedCart.getId());
            bill.setTotalitem(orderItem.getQuantity());
            bill.setTotalcost(orderItem.getCost() * orderItem.getQuantity());
            billRepo.save(bill);
            return orderItem;
        }
    }

    @Override
    public OrderItem decreaseQuantity(OrderItem orderItem) throws GlobalException{
        OrderItem orderItemInfo= orderItemRepo.findById(orderItem.getId()).get();
        Bill bill= billRepo.findByCartId(orderItemInfo.getCartid()).get();
        bill.setBilldate(LocalDateTime.now().toString());
        bill.setTotalitem(bill.getTotalitem() - 1);
        bill.setTotalcost(bill.getTotalcost() - orderItemInfo.getCost());
        billRepo.save(bill);

        if(orderItem.getQuantity() > 1) {
            orderItemInfo.setQuantity(orderItemInfo.getQuantity() - 1);
            return orderItemRepo.save(orderItemInfo);
        }
            orderItemRepo.deleteById(orderItem.getId());
            return orderItemRepo.findById(orderItem.getId()).get();
    }
}
