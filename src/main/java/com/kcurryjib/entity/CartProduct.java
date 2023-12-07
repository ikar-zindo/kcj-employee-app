package com.kcurryjib.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

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
      CartProduct that = (CartProduct) o;
      return id == that.id && quantity == that.quantity && Objects.equals(cart, that.cart) &&
              Objects.equals(product, that.product) && Objects.equals(cratedAt, that.cratedAt);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, cart, product, quantity, cratedAt);
   }

   @Override
   public String toString() {
      return "CartProduct{" +
              "id=" + id +
              ", cart=" + cart +
              ", product=" + product +
              ", quantity=" + quantity +
              ", cratedAt=" + cratedAt +
              '}';
   }
}
