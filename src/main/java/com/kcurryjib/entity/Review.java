package com.kcurryjib.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "review")
public class Review {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "review_id")
   private long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "restaurant_id")
   private Restaurant restaurant;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "customer_id")
   private Customer customer;

   @Column(name = "rating")
   private BigDecimal rating;

   @Column(name = "comment")
   private String comment;

   @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
   private LocalDateTime createdAt;

   public Review() {
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public Restaurant getRestaurant() {
      return restaurant;
   }

   public void setRestaurant(Restaurant restaurant) {
      this.restaurant = restaurant;
   }

   public Customer getCustomer() {
      return customer;
   }

   public void setCustomer(Customer customer) {
      this.customer = customer;
   }

   public BigDecimal getRating() {
      return rating;
   }

   public void setRating(BigDecimal rating) {
      this.rating = rating;
   }

   public String getComment() {
      return comment;
   }

   public void setComment(String comment) {
      this.comment = comment;
   }

   public LocalDateTime getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(LocalDateTime createdAt) {
      this.createdAt = createdAt;
   }
}
