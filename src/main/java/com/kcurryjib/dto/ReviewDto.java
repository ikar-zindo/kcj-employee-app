package com.kcurryjib.dto;

import com.kcurryjib.entity.Customer;
import com.kcurryjib.entity.Restaurant;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class ReviewDto {

   private long id;
   private Restaurant restaurant;
   private Customer customer;
   private BigDecimal rating;
   private String comment;
   private LocalDateTime createdAt;

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

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ReviewDto reviewDto = (ReviewDto) o;
      return id == reviewDto.id && Objects.equals(restaurant, reviewDto.restaurant) &&
              Objects.equals(customer, reviewDto.customer) && Objects.equals(rating, reviewDto.rating) &&
              Objects.equals(comment, reviewDto.comment) && Objects.equals(createdAt, reviewDto.createdAt);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, restaurant, customer, rating, comment, createdAt);
   }

   @Override
   public String toString() {
      return "ReviewDto{" +
              "id=" + id +
              ", restaurant=" + restaurant +
              ", customer=" + customer +
              ", rating=" + rating +
              ", comment='" + comment + '\'' +
              ", createdAt=" + createdAt +
              '}';
   }
}
