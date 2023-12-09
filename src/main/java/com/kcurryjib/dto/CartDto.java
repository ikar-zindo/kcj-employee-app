package com.kcurryjib.dto;

import com.kcurryjib.entity.CartProduct;
import com.kcurryjib.entity.Customer;

import java.util.List;
import java.util.Objects;

public class CartDto {

   private long id;
   private Customer customer;
   private List<CartProduct> cartProducts;

   public CartDto() {
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
      CartDto cartDto = (CartDto) o;
      return id == cartDto.id && Objects.equals(customer, cartDto.customer) &&
              Objects.equals(cartProducts, cartDto.cartProducts);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, customer, cartProducts);
   }

   @Override
   public String toString() {
      return "CartDto{" +
              "id=" + id +
              ", customer=" + customer +
              ", cartProducts=" + cartProducts +
              '}';
   }
}
