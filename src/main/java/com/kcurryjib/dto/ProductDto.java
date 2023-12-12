package com.kcurryjib.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public class ProductDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private long id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private String name;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private String description;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private BigDecimal price;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private LocalDateTime createdAt;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private boolean isAvailable;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("restaurant")
   private RestaurantDto restaurantDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("cartProducts")
   private List<CartProductDto> cartProductsDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("orderProducts")
   private List<OrderProductDto> orderProductsDto;

   public ProductDto() {
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public BigDecimal getPrice() {
      return price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public LocalDateTime getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(LocalDateTime createdAt) {
      this.createdAt = createdAt;
   }

   public boolean isAvailable() {
      return isAvailable;
   }

   public void setAvailable(boolean available) {
      isAvailable = available;
   }

   public RestaurantDto getRestaurantDto() {
      return restaurantDto;
   }

   public void setRestaurantDto(RestaurantDto restaurantDto) {
      this.restaurantDto = restaurantDto;
   }

   public List<CartProductDto> getCartProductsDto() {
      return cartProductsDto;
   }

   public void setCartProductsDto(List<CartProductDto> cartProductsDto) {
      this.cartProductsDto = cartProductsDto;
   }

   public List<OrderProductDto> getOrderProductsDto() {
      return orderProductsDto;
   }

   public void setOrderProductsDto(List<OrderProductDto> orderProductsDto) {
      this.orderProductsDto = orderProductsDto;
   }
}
