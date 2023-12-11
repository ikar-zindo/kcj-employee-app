package com.kcurryjib.dto;

import java.time.LocalDateTime;


public class CartProductDto {

   private long id;

   private CartDto cartDto;

   private ProductDto productDto;

   private int quantity;

   private LocalDateTime cratedAt;

   public CartProductDto() {
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public CartDto getCartDto() {
      return cartDto;
   }

   public void setCartDto(CartDto cartDto) {
      this.cartDto = cartDto;
   }

   public ProductDto getProductDto() {
      return productDto;
   }

   public void setProductDto(ProductDto productDto) {
      this.productDto = productDto;
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
