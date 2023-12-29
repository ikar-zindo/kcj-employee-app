package com.kcurryjib.entity;

import com.kcurryjib.entity.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "employee_id")
   private Long id;


   @Column(name = "first_name")
//   @Pattern(regexp = "[A-Z][a-z]{1,49}", message = "a string should start with a capital letter (rest lowercase) and contain at least two letters")
   private String firstName;

   @Column(name = "last_name")
//   @Pattern(regexp = "[A-Z][a-z]{1,49}", message = "a string should start with a capital letter (rest lowercase) and contain at least two letters")
   private String lastName;

//   @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Email is not valid")
   @Column(name = "email")
   private String email;

   @Column(name = "nickname")
   private String nickname;

   @Enumerated(EnumType.STRING)
   @Column(name = "role")
   private Role role;

   @Column(name = "password")
   private String password;

//   @NotBlank(message = "Phone cant be empty")
//   @Pattern(regexp = "\\+\\d{8,15}", message = "Phone is not valid")
   @Column(name = "phone_number")
   private String phoneNumber;

   @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
   private LocalDateTime createdAt;

   @Column(name = "is_active")
   private boolean isActive;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "restaurant_id")
   private Restaurant restaurant;

   @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
   private List<Order> orders;

   public Employee() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
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

   public String getNickname() {
      return nickname;
   }

   public void setNickname(String nickname) {
      this.nickname = nickname;
   }

   public Role getRole() {
      return role;
   }

   public void setRole(Role role) {
      this.role = role;
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

   public LocalDateTime getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(LocalDateTime createdAt) {
      this.createdAt = createdAt;
   }

   public boolean isActive() {
      return isActive;
   }

   public void setActive(boolean active) {
      isActive = active;
   }

   public Restaurant getRestaurant() {
      return restaurant;
   }

   public void setRestaurant(Restaurant restaurant) {
      this.restaurant = restaurant;
   }

   public List<Order> getOrders() {
      return orders;
   }

   public void setOrders(List<Order> orders) {
      this.orders = orders;
   }
}
