package com.foodDelivery.FoodDelivery.restuarant.service;

import com.foodDelivery.FoodDelivery.restuarant.entity.Cart;
import com.foodDelivery.FoodDelivery.restuarant.exception.GlobalException;
import com.foodDelivery.FoodDelivery.restuarant.repository.BillRepository;
import com.foodDelivery.FoodDelivery.restuarant.repository.CartRepository;
import com.foodDelivery.FoodDelivery.restuarant.repository.OrderItemRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepo;

    @Autowired
    OrderItemRespository orderItemRepo;

    @Autowired
    BillRepository billRepo;

    @Override
    public Cart deleteCartById(Integer cartId) throws GlobalException {
        Cart cart= cartRepo.findById(cartId).get();
        cartRepo.deleteById(cartId);
        return cart;
    }

    @Override
    public String deleteAllOrderItems(Integer cartId) throws GlobalException{
        orderItemRepo.deleteAllByCartid(cartId);
        billRepo.deleteByCartId(cartId);
        return "All Items deleted from the cart";
    }

    @Override
    public Cart getCartById(Integer cartId) throws GlobalException{
        Optional<Cart> cart= cartRepo.findById(cartId);
        if(cart.isPresent())
            return cart.get();

        throw new GlobalException("Cart of Id"+" "+"does not exist");
    }

    @Override
    public List<Cart> getAllByRestuarantId(Integer restuarantId) throws GlobalException{
        List<Cart> cart= cartRepo.findAllByRestuarantId(restuarantId);
        if(cart.isEmpty())
            throw new GlobalException("Carts does not exist for restuarant id"+" "+restuarantId);

        return cart;
    }

    @Override
    public List<Cart> getAllRestuarantCart() throws GlobalException{
        List<Cart> carts= cartRepo.findAll();
        if(carts.isEmpty())
            throw new GlobalException("No carts available");

        else
            return carts;
    }

    @Override
    public List<Cart> getAllByUserId(Integer userId) throws GlobalException{
        List<Cart> carts= cartRepo.findAllByUserId(userId);
        if(carts.isEmpty()){
            throw new GlobalException("No carts available");
        }

        return carts;
    }





}
