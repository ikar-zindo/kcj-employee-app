package com.kcurryjib.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {

   private long id;

   @JsonProperty("customer")
   private CustomerDto customerDto;

   @JsonProperty("restaurant")
   private RestaurantDto restaurantDto;

   @JsonProperty("employee")
   private EmployeeDto employeeDto;

   private LocalDateTime orderDate;

   private String deliveryAddress;

   private BigDecimal totalAmount;

   private String orderStatus;

   @JsonProperty("orderProducts")
   private List<OrderProductDto> orderProductsDto;

   public OrderDto() {
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
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

   public String getOrderStatus() {
      return orderStatus;
   }

   public void setOrderStatus(String orderStatus) {
      this.orderStatus = orderStatus;
   }

   public List<OrderProductDto> getOrderProductsDto() {
      return orderProductsDto;
   }

   public void setOrderProductsDto(List<OrderProductDto> orderProductsDto) {
      this.orderProductsDto = orderProductsDto;
   }
}
