package com.foodDelivery.FoodDelivery.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Integer> {
    Optional<Token> findByToken(String token);
    Optional<Token> findByUserId(Integer userId);

    public void deleteByToken(String token);
}
