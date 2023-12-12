package com.kcurryjib.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "cart_id")
   private long id;

   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
   private Customer customer;

   @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
   private List<CartProduct> cartProducts;

   public Cart() {
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public Customer getCustomer() {
      return customer;
   }

   public void setCustomer(Customer customer) {
      this.customer = customer;
   }

   public List<CartProduct> getCartProducts() {
      return cartProducts;
   }

   public void setCartProducts(List<CartProduct> cartProducts) {
      this.cartProducts = cartProducts;
   }
}
