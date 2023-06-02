package com.foodDelivery.FoodDelivery.restuarant.repository;

import com.foodDelivery.FoodDelivery.restuarant.entity.Restuarant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestuarantRepository extends JpaRepository<Restuarant,Integer> {
    Optional<Restuarant> findByName(String name);
    List<Restuarant> findByBestFor(String bestFor);

    @Query("SELECT p FROM Restuarant p WHERE CONCAT(p.name, ' ', p.address.city, ' ', p.address.state, ' ', p.address.country, ' ', p.bestFor) LIKE %?1%")
    public List<Restuarant> search(String keyword);

}
