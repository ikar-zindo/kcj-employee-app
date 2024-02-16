package com.kcurryjib.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kcurryjib.entity.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


public class EmployeeDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Long id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotEmpty(message = "{validation.length.empty}")
   @Length(max = 30, message = "{validation.length.max.30}")
   private String firstName;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotEmpty(message = "{validation.length.empty}")
   @Length(max = 30, message = "{validation.length.max.30}")
   private String lastName;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotEmpty(message = "{validation.length.empty}")
   @Email(message = "{validation.value.email}")
   private String email;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotEmpty(message = "{validation.length.empty}")
   @Length(max = 60, message = "{validation.length.max.60}")
   private String username;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Role role;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotEmpty(message = "{validation.length.empty}")
   @Length(max = 120, message = "{validation.length.max.120}")
   private String password;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotEmpty(message = "{validation.length.empty}")
   @Pattern(regexp = "^\\+\\d{2}\\s\\d{3}\\s\\d{3}\\s\\d{3}$", message = "{validation.value.phoneNumber}")
   private String phoneNumber;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private LocalDateTime createdAt;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Boolean isActive;

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

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
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

   public Boolean getActive() {
      return isActive;
   }

   public void setActive(Boolean active) {
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

   // Builder class
   public static class Builder {

      private EmployeeDto employeeDto = new EmployeeDto();

      public Builder id(Long id) {
         employeeDto.id = id;
         return this;
      }

      public Builder firstName(String firstName) {
         employeeDto.firstName = firstName;
         return this;
      }

      public Builder lastName(String lastName) {
         employeeDto.lastName = lastName;
         return this;
      }

      public Builder email(String email) {
         employeeDto.email = email;
         return this;
      }

      public Builder nickname(String nickname) {
         employeeDto.username = nickname;
         return this;
      }

      public Builder role(Role role) {
         employeeDto.role = role;
         return this;
      }

      public Builder password(String password) {
         employeeDto.password = password;
         return this;
      }

      public Builder phoneNumber(String phoneNumber) {
         employeeDto.phoneNumber = phoneNumber;
         return this;
      }

      public Builder createdAt(LocalDateTime createdAt) {
         employeeDto.createdAt = createdAt;
         return this;
      }

      public Builder isActive(Boolean isActive) {
         employeeDto.isActive = isActive;
         return this;
      }

      public Builder restaurantDto(RestaurantDto restaurantDto) {
         employeeDto.restaurantDto = restaurantDto;
         return this;
      }

      public EmployeeDto build() {
         return employeeDto;
      }
   }

   public static Builder builder() {
      return new Builder();
   }

   // Equals & HashCode
   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      EmployeeDto that = (EmployeeDto) o;
      return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) &&
              Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) &&
              Objects.equals(username, that.username) && role == that.role &&
              Objects.equals(password, that.password) && Objects.equals(phoneNumber, that.phoneNumber) &&
              Objects.equals(createdAt, that.createdAt) && Objects.equals(isActive, that.isActive) &&
              Objects.equals(restaurantDto, that.restaurantDto) && Objects.equals(ordersDto, that.ordersDto);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, firstName, lastName, email, username, role, password,
              phoneNumber, createdAt, isActive, restaurantDto, ordersDto);
   }

   // ToString
   @Override
   public String toString() {
      return "EmployeeDto{" +
              "id=" + id +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", email='" + email + '\'' +
              ", nickname='" + username + '\'' +
              ", role=" + role +
              ", password='" + password + '\'' +
              ", phoneNumber='" + phoneNumber + '\'' +
              ", createdAt=" + createdAt +
              ", isActive=" + isActive +
              ", restaurantDto=" + restaurantDto +
              ", ordersDto=" + ordersDto +
              '}';
   }
}
