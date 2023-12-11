package com.kcurryjib.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class ReviewDto {

   private long id;

   private RestaurantDto restaurantDto;

   private CustomerDto customerDto;

   private BigDecimal rating;

   private String comment;

   private LocalDateTime createdAt;

   public ReviewDto() {
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public RestaurantDto getRestaurantDto() {
      return restaurantDto;
   }

   public void setRestaurantDto(RestaurantDto restaurantDto) {
      this.restaurantDto = restaurantDto;
   }

   public CustomerDto getCustomerDto() {
      return customerDto;
   }

   public void setCustomerDto(CustomerDto customerDto) {
      this.customerDto = customerDto;
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
