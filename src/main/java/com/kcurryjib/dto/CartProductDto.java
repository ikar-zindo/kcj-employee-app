package com.kcurryjib.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kcurryjib.entity.Cart;
import com.kcurryjib.entity.Product;

import java.time.LocalDateTime;

public class CartProductDto {

   private long id;
   private Cart cart;
   private Product product;
   private int quantity;
   private LocalDateTime cratedAt;

   @JsonProperty("customer")
   private CustomerDto customerDto;

   public CartProductDto() {
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public Cart getCart() {
      return cart;
   }

   public void setCart(Cart cart) {
      this.cart = cart;
   }

   public Product getProduct() {
      return product;
   }

   public void setProduct(Product product) {
      this.product = product;
   }

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public LocalDateTime getCratedAt() {
      return cratedAt;
   }

   public void setCratedAt(LocalDateTime cratedAt) {
      this.cratedAt = cratedAt;
   }
}
