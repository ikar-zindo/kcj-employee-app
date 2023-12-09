package com.kcurryjib.dto;

import com.kcurryjib.entity.Customer;
import com.kcurryjib.entity.Employee;
import com.kcurryjib.entity.Restaurant;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class OrderDto {

   private long id;
   private Customer customer;
   private Restaurant restaurant;
   private Employee employee;
   private LocalDateTime orderDate;
   private String deliveryAddress;
   private BigDecimal totalAmount;
   private String orderStatus;

   public OrderDto() {
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public Customer getCustomer() {
      return customer;
   }

   public void setCustomer(Customer customer) {
      this.customer = customer;
   }

   public Restaurant getRestaurant() {
      return restaurant;
   }

   public void setRestaurant(Restaurant restaurant) {
      this.restaurant = restaurant;
   }

   public Employee getEmployee() {
      return employee;
   }

   public void setEmployee(Employee employee) {
      this.employee = employee;
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

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      OrderDto orderDto = (OrderDto) o;
      return id == orderDto.id && Objects.equals(customer, orderDto.customer) &&
              Objects.equals(restaurant, orderDto.restaurant) && Objects.equals(employee, orderDto.employee) &&
              Objects.equals(orderDate, orderDto.orderDate) &&
              Objects.equals(deliveryAddress, orderDto.deliveryAddress) &&
              Objects.equals(totalAmount, orderDto.totalAmount) && Objects.equals(orderStatus, orderDto.orderStatus);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, customer, restaurant, employee, orderDate, deliveryAddress, totalAmount, orderStatus);
   }

   @Override
   public String toString() {
      return "OrderDto{" +
              "id=" + id +
              ", customer=" + customer +
              ", restaurant=" + restaurant +
              ", employee=" + employee +
              ", orderDate=" + orderDate +
              ", deliveryAddress='" + deliveryAddress + '\'' +
              ", totalAmount=" + totalAmount +
              ", orderStatus='" + orderStatus + '\'' +
              '}';
   }
}
