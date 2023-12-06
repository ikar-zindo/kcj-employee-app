package com.kcurryjib.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "cart_product")
public class CartProduct {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "cart_product_id")
   private long id;

   @ManyToOne
   @JoinColumn(name = "cart_id")
   private Cart cart;

   @ManyToOne
   @JoinColumn(name = "product_id")
   private Product product;

   @Column(name = "quantity")
   private int quantity;

   @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
   private LocalDateTime cratedAt;

   public CartProduct() {
   }

   public CartProduct(Cart cart, Product product, int quantity) {
      this.cart = cart;
      this.product = product;
      this.quantity = quantity;
   }

}
