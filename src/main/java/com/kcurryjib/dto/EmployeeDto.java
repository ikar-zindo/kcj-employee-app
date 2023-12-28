package com.kcurryjib.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kcurryjib.entity.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;


public class EmployeeDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Long id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Length(max = 30, message = "{validation.length.max.30}")
   private String firstName;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Length(max = 30, message = "{validation.length.max.30}")
   private String lastName;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Email(message = "Invalid email")
   private String email;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Length(max = 60, message = "{validation.length.max.60}")
   private String nickname;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Role role;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Length(max = 120, message = "{validation.length.max.120}")
   private String password;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Pattern(regexp = "\\\\+49\\\\d{10}", message = "{validation.length.max.10}")
   private String phoneNumber;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private LocalDateTime createdAt;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private boolean isActive;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private RestaurantDto restaurantDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("orders")
   private List<OrderDto> ordersDto;

   public EmployeeDto() {
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

   public RestaurantDto getRestaurantDto() {
      return restaurantDto;
   }

   public void setRestaurantDto(RestaurantDto restaurantDto) {
      this.restaurantDto = restaurantDto;
   }

   public List<OrderDto> getOrdersDto() {
      return ordersDto;
   }

   public void setOrdersDto(List<OrderDto> ordersDto) {
      this.ordersDto = ordersDto;
   }
}
