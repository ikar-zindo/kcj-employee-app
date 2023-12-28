package com.kcurryjib.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;


public class CustomerDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Long id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Length(max = 60, message = "{validation.length.max.60}")
   private String firstName;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Length(max = 60, message = "{validation.length.max.60}")
   private String lastName;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Email(message = "Invalid email")
   @Length(max = 60, message = "{validation.length.max.60}")
   private String email;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Length(max = 120, message = "{validation.length.max.120}")
   private String password;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Pattern(regexp = "\\\\+49\\\\d{10}", message = "{validation.length.max.10}")
   private String phoneNumber;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private String address;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Length(max = 5, message = "{validation.length.max.5}")
   private String postalCode;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private LocalDateTime createdAt;

   private boolean isBlocked;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("cart")
   private CartDto cartDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("order")
   private List<OrderDto> ordersDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("review")
   private List<ReviewDto> reviewsDto;

   public CustomerDto() {
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

   public CartDto getCartDto() {
      return cartDto;
   }

   public void setCartDto(CartDto cartDto) {
      this.cartDto = cartDto;
   }

   public List<OrderDto> getOrdersDto() {
      return ordersDto;
   }

   public void setOrdersDto(List<OrderDto> ordersDto) {
      this.ordersDto = ordersDto;
   }

   public List<ReviewDto> getReviewsDto() {
      return reviewsDto;
   }

   public void setReviewsDto(List<ReviewDto> reviewsDto) {
      this.reviewsDto = reviewsDto;
   }
}
