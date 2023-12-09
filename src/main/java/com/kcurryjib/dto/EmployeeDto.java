package com.kcurryjib.dto;

import com.kcurryjib.entity.Restaurant;
import com.kcurryjib.entity.enums.Role;

import java.time.LocalDateTime;
import java.util.Objects;

public class EmployeeDto {

   private long id;
   private String firstName;
   private String lastName;
   private String email;
   private String nickname;
   private Role role;
   private Restaurant restaurant;
   private String password;
   private String phoneNumber;
   private LocalDateTime createdAt;
   private boolean isActive;

   public EmployeeDto() {
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

   public Restaurant getRestaurant() {
      return restaurant;
   }

   public void setRestaurant(Restaurant restaurant) {
      this.restaurant = restaurant;
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

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      EmployeeDto that = (EmployeeDto) o;
      return id == that.id && isActive == that.isActive && Objects.equals(firstName, that.firstName) &&
              Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) &&
              Objects.equals(nickname, that.nickname) && role == that.role &&
              Objects.equals(restaurant, that.restaurant) && Objects.equals(password, that.password) &&
              Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(createdAt, that.createdAt);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, firstName, lastName, email, nickname, role, restaurant, password, phoneNumber, createdAt, isActive);
   }

   @Override
   public String toString() {
      return "EmployeeDto{" +
              "id=" + id +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", email='" + email + '\'' +
              ", nickname='" + nickname + '\'' +
              ", role=" + role +
              ", restaurant=" + restaurant +
              ", password='" + password + '\'' +
              ", phoneNumber='" + phoneNumber + '\'' +
              ", createdAt=" + createdAt +
              ", isActive=" + isActive +
              '}';
   }
}
