package com.kcj_employee_app.entity;

import com.kcj_employee_app.entity.enums.Role;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customers")
public class Customer {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @UuidGenerator(style = UuidGenerator.Style.TIME)
   @Column(name = "customer_id")
   private UUID id;

   @Column(name = "first_name")
   private String firstName;

   @Column(name = "last_name")
   private String lastName;

   @Column(name = "email")
   private String email;

   @Column(name = "password")
   private String password;

   @Column(name = "phone_number")
   private String phoneNumber;

   @Column(name = "address")
   private String address;

   @Column(name = "postal_code")
   private String postalCode;

   @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
   private LocalDateTime createdAt;

   @Column(name = "updated_at")
   private LocalDateTime updatedAt;

   @Enumerated(EnumType.STRING)
   @Column(name = "role")
   private Role role;

   @Column(name = "is_blocked")
   private Boolean isBlocked;

   @OneToOne(mappedBy = "customer")
   private Cart cart;

   @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
   private List<Order> orders;

   @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
   private List<Review> reviews;

   public Customer() {
   }

   // Getters & Setters
   public UUID getId() {
      return id;
   }

   public void setId(UUID id) {
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

   public LocalDateTime getUpdatedAt() {
      return updatedAt;
   }

   public void setUpdatedAt(LocalDateTime updatedAt) {
      this.updatedAt = updatedAt;
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

   public Role getRole() {
      return role;
   }

   public void setRole(Role role) {
      this.role = role;
   }

   public Boolean getBlocked() {
      return isBlocked;
   }

   public void setBlocked(Boolean blocked) {
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

   // Builder class
   public static class Builder {

      private Customer customer = new Customer();

      public Builder id(UUID id) {
         customer.id = id;
         return this;
      }

      public Builder firstName(String firstName) {
         customer.firstName = firstName;
         return this;
      }

      public Builder lastName(String lastName) {
         customer.lastName = lastName;
         return this;
      }

      public Builder email(String email) {
         customer.email = email;
         return this;
      }

      public Builder password(String password) {
         customer.password = password;
         return this;
      }

      public Builder phoneNumber(String phoneNumber) {
         customer.phoneNumber = phoneNumber;
         return this;
      }

      public Builder address(String address) {
         customer.address = address;
         return this;
      }

      public Builder postalCode(String postalCode) {
         customer.postalCode = postalCode;
         return this;
      }

      public Builder createdAt(LocalDateTime createdAt) {
         customer.createdAt = createdAt;
         return this;
      }

      public Builder isBlocked(Boolean isBlocked) {
         customer.isBlocked = isBlocked;
         return this;
      }

      public Builder role(Role role) {
         customer.role = role;
         return this;
      }

      public Builder updatedAt(LocalDateTime updatedAt) {
         customer.updatedAt = updatedAt;
         return this;
      }

      public Customer build() {
         return customer;
      }
   }

   public static Builder builder() {
      return new Builder();
   }
}
