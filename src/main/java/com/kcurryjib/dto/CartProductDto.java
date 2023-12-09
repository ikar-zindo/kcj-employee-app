package com.kcurryjib.dto;

import com.kcurryjib.entity.Cart;
import com.kcurryjib.entity.Product;

import java.time.LocalDateTime;
import java.util.Objects;

public class CartProductDto {

   private long id;
   private Cart cart;
   private Product product;
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

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      CartProductDto that = (CartProductDto) o;
      return id == that.id && quantity == that.quantity && Objects.equals(cart, that.cart) &&
              Objects.equals(product, that.product) && Objects.equals(cratedAt, that.cratedAt);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, cart, product, quantity, cratedAt);
   }

   @Override
   public String toString() {
      return "CartProductDto{" +
              "id=" + id +
              ", cart=" + cart +
              ", product=" + product +
              ", quantity=" + quantity +
              ", cratedAt=" + cratedAt +
              '}';
   }
}
