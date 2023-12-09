package com.kcurryjib.dto;

import com.kcurryjib.entity.Cart;
import com.kcurryjib.entity.CartProduct;
import com.kcurryjib.entity.OrderProduct;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class CustomerDto {

   private long id;
   private String firstName;
   private String lastName;
   private String email;
   private String phoneNumber;
   private String address;
   private String postal_code;
   private String password;
   private Cart cart;
   private LocalDateTime createdAt;
   private boolean isBlocked;
   private List<CartProduct> cartProducts;
   private List<OrderProduct> orderProducts;

   public CustomerDto() {
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      CustomerDto that = (CustomerDto) o;
      return id == that.id && isBlocked == that.isBlocked && Objects.equals(firstName, that.firstName) &&
              Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) &&
              Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(address, that.address) &&
              Objects.equals(postal_code, that.postal_code) && Objects.equals(password, that.password) &&
              Objects.equals(cart, that.cart) && Objects.equals(createdAt, that.createdAt) &&
              Objects.equals(cartProducts, that.cartProducts) && Objects.equals(orderProducts, that.orderProducts);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, firstName, lastName, email, phoneNumber, address, postal_code, password, cart, createdAt, isBlocked, cartProducts, orderProducts);
   }

   @Override
   public String toString() {
      return "CustomerDto{" +
              "id=" + id +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", email='" + email + '\'' +
              ", phoneNumber='" + phoneNumber + '\'' +
              ", address='" + address + '\'' +
              ", postal_code='" + postal_code + '\'' +
              ", password='" + password + '\'' +
              ", cart=" + cart +
              ", createdAt=" + createdAt +
              ", isBlocked=" + isBlocked +
              ", cartProducts=" + cartProducts +
              ", orderProducts=" + orderProducts +
              '}';
   }
}
