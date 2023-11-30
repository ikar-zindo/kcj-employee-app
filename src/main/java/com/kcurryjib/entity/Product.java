package com.kcurryjib.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
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

   @Column(name = "created_at")
   private Timestamp createdAt;

   @Column(name = "available")
   private boolean available;

   public Product() {
   }

   public Product(String name, String description, BigDecimal price) {
      this.name = name;
      this.description = description;
      this.price = price;

      createdAt = Timestamp.from(Instant.now());
      this.available = true;
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

   public Timestamp getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(Timestamp createdAt) {
      this.createdAt = createdAt;
   }

   public boolean isAvailable() {
      return available;
   }

   public void setAvailable(boolean available) {
      this.available = available;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Product product = (Product) o;
      return id == product.id && available == product.available && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(price, product.price) && Objects.equals(createdAt, product.createdAt);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, name, description, price, createdAt, available);
   }

   @Override
   public String toString() {
      return getClass().getSimpleName() + '{' +
              "id=" + id +
              ", name='" + name + '\'' +
              ", description='" + description + '\'' +
              ", price=" + price +
              ", createdAt=" + createdAt +
              ", available=" + available +
              '}';
   }
}
