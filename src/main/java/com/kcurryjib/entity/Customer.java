package com.kcurryjib.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "customer_id")
   private long id;

   @Pattern(regexp = "[A-Z][a-z]{1,49}", message = "a string should start with a capital letter (rest lowercase) and contain at least two letters")
   @Column(name = "first_name")
   private String firstName;

   @Pattern(regexp = "[A-Z][a-z]{1,49}", message = "a string should start with a capital letter (rest lowercase) and contain at least two letters")
   @Column(name = "last_name")
   private String lastName;

   @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Email is not valid")
   @Column(name = "email")
   private String email;

   @Column(name = "password")
   private String password;

   @NotBlank(message = "Phone cant be empty")
   @Pattern(regexp = "\\+\\d{8,15}", message = "Phone is not valid")
   @Column(name = "phone_number")
   private String phoneNumber;

   @NotBlank(message = "Address cant be empty")
   @Column(name = "address")
   private String address;

   @Pattern(regexp = "^\\d{5}$", message = "Must save 5 digits")
   @Column(name = "postal_code")
   private String postalCode;

   @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
   private LocalDateTime createdAt;

   @Column(name = "is_blocked")
   private boolean isBlocked;

   @OneToOne(mappedBy = "customer")
   private Cart cart;

   @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
   private List<Order> orders;

   @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
   private List<Review> reviews;

   public Customer() {
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

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
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

   public String getPostalCode() {
      return postalCode;
   }

   public void setPostalCode(String postalCode) {
      this.postalCode = postalCode;
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

   public Cart getCart() {
      return cart;
   }

   public void setCart(Cart cart) {
      this.cart = cart;
   }

   public List<Order> getOrders() {
      return orders;
   }

   public void setOrders(List<Order> orders) {
      this.orders = orders;
   }

   public List<Review> getReviews() {
      return reviews;
   }

   public void setReviews(List<Review> reviews) {
      this.reviews = reviews;
   }
}
