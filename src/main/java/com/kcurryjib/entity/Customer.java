package com.kcurryjib.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "customer_id")
   private long id;

   @Column(name = "first_name")
   @Pattern(regexp = "[A-Z][a-z]{1,49}", message = "a string should start with a capital letter (rest lowercase) and contain at least two letters")
   private String firstName;

   @Column(name = "last_name")
   @Pattern(regexp = "[A-Z][a-z]{1,49}", message = "a string should start with a capital letter (rest lowercase) and contain at least two letters")
   private String lastName;

   @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Email is not valid")
   @Column(name = "email")
   private String email;

   @NotBlank(message = "Phone cant be empty")
   @Pattern(regexp = "\\+\\d{8,15}", message = "Phone is not valid")
   @Column(name = "phone_number")
   private String phoneNumber;

   @NotBlank(message = "Address cant be empty")
   @Column(name = "address")
   private String address;

   @Pattern(regexp = "^\\d{5}$", message = "Must save 5 digits")
   @Column(name = "postal_code")
   private String postal_code;

   @Column(name = "password")
   private String password;

   @OneToOne(mappedBy = "customer")
   private Cart cart;

   @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
   private LocalDateTime createdAt;

   @Column(name = "is_blocked")
   private boolean isBlocked;

   /**
    * Здесь было в скобках (mappedBy = "customer"),
    * но в таком случае добавляется поле (customer_cart_product_id)
    */
   @OneToMany
   @JoinTable(name = "cart_product")
   private List<CartProduct> cartProducts;

   @OneToMany
   @JoinTable(name = "order_product")
   private List<OrderProduct> orderProducts;

   public Customer() {
   }

   public Customer(String firstName, String lastName, String email, String phoneNumber,
                   String address, String postal_code, String password, Cart cart) {

      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.phoneNumber = phoneNumber;
      this.address = address;
      this.postal_code = postal_code;
      this.password = password;
      this.cart = cart;

      isBlocked = false;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getPostal_code() {
      return postal_code;
   }

   public void setPostal_code(String postal_code) {
      this.postal_code = postal_code;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Cart getCart() {
      return cart;
   }

   public void setCart(Cart cart) {
      this.cart = cart;
   }

   public LocalDateTime getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(LocalDateTime createdAt) {
      this.createdAt = createdAt;
   }

   public boolean isBlocked() {
      return isBlocked;
   }

   public void setBlocked(boolean blocked) {
      isBlocked = blocked;
   }

   public List<CartProduct> getCartProducts() {
      return cartProducts;
   }

   public void setCartProducts(List<CartProduct> cartProducts) {
      this.cartProducts = cartProducts;
   }

   public List<OrderProduct> getOrderProducts() {
      return orderProducts;
   }

   public void setOrderProducts(List<OrderProduct> orderProducts) {
      this.orderProducts = orderProducts;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Customer customer = (Customer) o;
      return id == customer.id && isBlocked == customer.isBlocked && Objects.equals(firstName, customer.firstName) &&
              Objects.equals(lastName, customer.lastName) && Objects.equals(email, customer.email) &&
              Objects.equals(phoneNumber, customer.phoneNumber) && Objects.equals(address, customer.address) &&
              Objects.equals(postal_code, customer.postal_code) && Objects.equals(password, customer.password) &&
              Objects.equals(cart, customer.cart) && Objects.equals(createdAt, customer.createdAt) &&
              Objects.equals(cartProducts, customer.cartProducts) && Objects.equals(orderProducts, customer.orderProducts);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, firstName, lastName, email, phoneNumber, address, postal_code, password, cart, createdAt, isBlocked, cartProducts, orderProducts);
   }

   @Override
   public String toString() {
      return "Customer{" +
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
