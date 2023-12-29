package com.kcurryjib.entity;

import com.kcurryjib.entity.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee implements UserDetails {

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
   private Boolean isActive;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "restaurant_id")
   private Restaurant restaurant;

   @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
   private List<Order> orders;

   public Employee() {
   }

   // Getters & Setters

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

   public Boolean getActive() {
      return isActive;
   }

   public void setActive(Boolean active) {
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

   // Override methods UserDetails
   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return Collections.singleton(role);
   }

   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return nickname;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Employee employee = (Employee) o;
      return Objects.equals(id, employee.id) && Objects.equals(firstName, employee.firstName) &&
              Objects.equals(lastName, employee.lastName) && Objects.equals(email, employee.email) &&
              Objects.equals(nickname, employee.nickname) && role == employee.role &&
              Objects.equals(password, employee.password) && Objects.equals(phoneNumber, employee.phoneNumber) &&
              Objects.equals(createdAt, employee.createdAt) && Objects.equals(isActive, employee.isActive) &&
              Objects.equals(restaurant, employee.restaurant) && Objects.equals(orders, employee.orders);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, firstName, lastName, email, nickname, role, password,
              phoneNumber, createdAt, isActive, restaurant, orders);
   }

   @Override
   public String toString() {
      return "Employee{" +
              "id=" + id +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", email='" + email + '\'' +
              ", nickname='" + nickname + '\'' +
              ", role=" + role +
              ", password='" + password + '\'' +
              ", phoneNumber='" + phoneNumber + '\'' +
              ", createdAt=" + createdAt +
              ", isActive=" + isActive +
              ", restaurant=" + restaurant +
              ", orders=" + orders +
              '}';
   }
}
