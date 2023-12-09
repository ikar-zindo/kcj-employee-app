package com.kcurryjib.dto;

import com.kcurryjib.entity.CartProduct;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ProductDto {

   private long id;
   private String name;
   private String description;
   private BigDecimal price;
   private LocalDateTime createdAt;
   private boolean isAvailable;
   private List<CartProduct> cartProducts;

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

   public List<CartProduct> getCartProducts() {
      return cartProducts;
   }

   public void setCartProducts(List<CartProduct> cartProducts) {
      this.cartProducts = cartProducts;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ProductDto that = (ProductDto) o;
      return id == that.id && isAvailable == that.isAvailable && Objects.equals(name, that.name) &&
              Objects.equals(description, that.description) && Objects.equals(price, that.price) &&
              Objects.equals(createdAt, that.createdAt) && Objects.equals(cartProducts, that.cartProducts);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, name, description, price, createdAt, isAvailable, cartProducts);
   }

   @Override
   public String toString() {
      return "ProductDto{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", description='" + description + '\'' +
              ", price=" + price +
              ", createdAt=" + createdAt +
              ", isAvailable=" + isAvailable +
              ", cartProducts=" + cartProducts +
              '}';
   }
}
