package com.kcurryjib.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "product_id")
   private long id;

   @Column(name = "name")
   private String name;

   @Column(name = "description")
   private String description;

   @Column(name = "price")
   private BigDecimal price;

   @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
   private LocalDateTime createdAt;

   @Column(name = "is_available")
   private boolean isAvailable;

   @OneToMany(mappedBy = "product")
   private List<CartProduct> cartProducts;

   public Product() {
   }

   public Product(String name, String description, BigDecimal price) {
      this.name = name;
      this.description = description;
      this.price = price;

      this.isAvailable = true;
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
      Product product = (Product) o;
      return id == product.id && isAvailable == product.isAvailable && Objects.equals(name, product.name) &&
              Objects.equals(description, product.description) && Objects.equals(price, product.price) &&
              Objects.equals(createdAt, product.createdAt) && Objects.equals(cartProducts, product.cartProducts);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, name, description, price, createdAt, isAvailable, cartProducts);
   }

   @Override
   public String toString() {
      return "Product{" +
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
