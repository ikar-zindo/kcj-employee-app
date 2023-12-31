package com.kcurryjib.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kcurryjib.entity.Review;
import com.kcurryjib.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public class OrderDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Long id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("customer")
   private CustomerDto customerDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("restaurant")
   private RestaurantDto restaurantDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("employee")
   private EmployeeDto employeeDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private LocalDateTime orderDate;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private String deliveryAddress;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private BigDecimal totalAmount;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private OrderStatus orderStatus;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("orderProducts")
   private List<OrderProductDto> orderProductsDto;

   public OrderDto() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public CustomerDto getCustomerDto() {
      return customerDto;
   }

   public void setCustomerDto(CustomerDto customerDto) {
      this.customerDto = customerDto;
   }

   public RestaurantDto getRestaurantDto() {
      return restaurantDto;
   }

   public void setRestaurantDto(RestaurantDto restaurantDto) {
      this.restaurantDto = restaurantDto;
   }

   public EmployeeDto getEmployeeDto() {
      return employeeDto;
   }

   public void setEmployeeDto(EmployeeDto employeeDto) {
      this.employeeDto = employeeDto;
   }

   public LocalDateTime getOrderDate() {
      return orderDate;
   }

   public void setOrderDate(LocalDateTime orderDate) {
      this.orderDate = orderDate;
   }

   public String getDeliveryAddress() {
      return deliveryAddress;
   }

   public void setDeliveryAddress(String deliveryAddress) {
      this.deliveryAddress = deliveryAddress;
   }

   public BigDecimal getTotalAmount() {
      return totalAmount;
   }

   public void setTotalAmount(BigDecimal totalAmount) {
      this.totalAmount = totalAmount;
   }

   public OrderStatus getOrderStatus() {
      return orderStatus;
   }

   public void setOrderStatus(OrderStatus orderStatus) {
      this.orderStatus = orderStatus;
   }

   public List<OrderProductDto> getOrderProductsDto() {
      return orderProductsDto;
   }

   public void setOrderProductsDto(List<OrderProductDto> orderProductsDto) {
      this.orderProductsDto = orderProductsDto;
   }

   // Builder class
   public static class Builder {

      private OrderDto orderDto = new OrderDto();

      public Builder id(Long id) {
         orderDto.id = id;
         return this;
      }

      public Builder customerDto(CustomerDto customerDto) {
         orderDto.customerDto = customerDto;
         return this;
      }

      public Builder restaurantDto(RestaurantDto restaurantDto) {
         orderDto.restaurantDto = restaurantDto;
         return this;
      }

      public Builder employeeDto(EmployeeDto employeeDto) {
         orderDto.employeeDto = employeeDto;
         return this;
      }

      public Builder orderDate(LocalDateTime orderDate) {
         orderDto.orderDate = orderDate;
         return this;
      }

      public Builder deliveryAddress(String deliveryAddress) {
         orderDto.deliveryAddress = deliveryAddress;
         return this;
      }

      public Builder totalAmount(BigDecimal totalAmount) {
         orderDto.totalAmount = totalAmount;
         return this;
      }

      public Builder orderStatus(OrderStatus orderStatus) {
         orderDto.orderStatus = orderStatus;
         return this;
      }

      public OrderDto build() {
         return orderDto;
      }
   }

   public static Review.Builder builder() {
      return new Review.Builder();
   }
}
