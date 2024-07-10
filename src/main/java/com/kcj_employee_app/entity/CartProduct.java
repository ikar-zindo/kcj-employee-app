package com.kcj_employee_app.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "cart_products")
public class CartProduct {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @UuidGenerator(style = UuidGenerator.Style.TIME)
   @Column(name = "cart_product_id")
   private UUID id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "cart_id")
   private Cart cart;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "product_id")
   private Product product;

   @Column(name = "quantity")
   private int quantity;

   @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
   private LocalDateTime cratedAt;

   public CartProduct() {
   }

   public UUID getId() {
      return id;
   }

   public void setId(UUID id) {
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

   // Builder class
   public static class Builder {

      private CartProduct cartProduct = new CartProduct();

      public Builder id(UUID id) {
         cartProduct.id = id;
         return this;
      }

      public Builder cart(Cart cart) {
         cartProduct.cart = cart;
         return this;
      }

      public Builder product(Product product) {
         cartProduct.product = product;
         return this;
      }

      public Builder cratedAt(LocalDateTime cratedAt) {
         cartProduct.cratedAt = cratedAt;
         return this;
      }

      public CartProduct build() {
         return cartProduct;
      }
   }

   public static Builder builder() {
      return new Builder();
   }

   // Override
}
