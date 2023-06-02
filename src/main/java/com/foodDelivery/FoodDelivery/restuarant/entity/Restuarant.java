package com.foodDelivery.FoodDelivery.restuarant.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Restuarant {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @Column(nullable = false)
   private String name;

   @OneToOne(cascade = CascadeType.ALL)
   private Address address;

   @Column(nullable = false)
   private String openingTime;

   @Column(nullable = false)
   private String closingTime;

   @Column(nullable = false)
   private float rating;

   private String image;

   public String getBestFor() {
      return bestFor;
   }

   public void setBestFor(String bestFor) {
      this.bestFor = bestFor;
   }

   @Column(nullable = false)
   private int minOrder;

   @Column(nullable = false)
   private float minCost;

   public int getDeliverytime() {
      return deliverytime;
   }

   public void setDeliverytime(int deliverytime) {
      this.deliverytime = deliverytime;
   }

   @Column(nullable = false)
   private int deliverytime;

   @Column(nullable = false)
   private String bestFor;

   @OneToMany(mappedBy ="restuarant",cascade = CascadeType.ALL)
   private List<Item> itemList = new ArrayList<>();

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Address getAddress() {
      return address;
   }

   public void setAddress(Address address) {
      this.address = address;
   }

   public String getOpeningTime() {
      return openingTime;
   }

   public void setOpeningTime(String openingTime) {
      this.openingTime = openingTime;
   }

   public String getClosingTime() {
      return closingTime;
   }

   public void setClosingTime(String closingTime) {
      this.closingTime = closingTime;
   }

   public float getRating() {
      return rating;
   }

   public void setRating(float rating) {
      this.rating = rating;
   }


   public int getMinOrder() {
      return minOrder;
   }

   public void setMinOrder(int minOrder) {
      this.minOrder = minOrder;
   }

   public float getMinCost() {
      return minCost;
   }

   public void setMinCost(float minCost) {
      this.minCost = minCost;
   }

   @JsonManagedReference
   public List<Item> getItemList() {
      return itemList;
   }

   public void setItemList(List<Item> itemList) {
      this.itemList = itemList;
   }

   public Restuarant(String name, Address address, String openingTime, String closingTime, float rating, int minOrder, float minCost,String bestFor,int deliverytime) {
      this.name = name;
      this.address = address;
      this.openingTime = openingTime;
      this.closingTime = closingTime;
      this.rating = rating;
      this.minOrder = minOrder;
      this.minCost = minCost;
      this.bestFor= bestFor;
      this.deliverytime= deliverytime;
   }

   public Restuarant(int id, String name, Address address, String openingTime, String closingTime, float rating, String image, int minOrder, float minCost,String bestFor,int deliverytime) {
      this.id = id;
      this.name = name;
      this.address = address;
      this.openingTime = openingTime;
      this.closingTime = closingTime;
      this.rating = rating;
      this.image = image;
      this.minOrder = minOrder;
      this.minCost = minCost;
      this.bestFor= bestFor;
      this.deliverytime= deliverytime;
   }
}
