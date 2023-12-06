package com.kcurryjib.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cart")
public class Cart {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "cart_id")
   private long id;

   @OneToOne
   @JoinColumn(name = "customer_id")
   private Customer customer;

   @OneToMany(mappedBy = "cart")
   private List<CartProduct> cartProducts;

   public Cart() {
   }

   public Cart(Customer customer) {
      this.customer = customer;
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

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Cart cart = (Cart) o;
      return id == cart.id && Objects.equals(customer, cart.customer) &&
              Objects.equals(cartProducts, cart.cartProducts);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, customer, cartProducts);
   }

   @Override
   public String toString() {
      return "Cart{" +
              "id=" + id +
              ", customer=" + customer +
              ", CartProducts=" + cartProducts +
              '}';
   }
}
