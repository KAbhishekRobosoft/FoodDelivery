package com.foodDelivery.FoodDelivery.user.refreshToken;

import com.foodDelivery.FoodDelivery.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    public void deleteAllByUserId(int userId);

    @Modifying
    int deleteByUser(User user);

    List<RefreshToken> findByUserId(int userId);
}
