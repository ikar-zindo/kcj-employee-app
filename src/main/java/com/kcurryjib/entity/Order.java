package com.kcurryjib.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "\"order\"")
public class Order {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "order_id")
   private long id;

   @ManyToOne
   @JoinColumn(name = "customer_id")
   private Customer customer;

   @OneToOne
   @JoinColumn(name = "restaurant_id")
   private Restaurant restaurant;

   @OneToOne
   @JoinColumn(name = "employee_id")
   private Employee employee;

   @Column(name = "order_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
   private LocalDateTime orderDate;

   @Column(name = "delivery_address")
   private String deliveryAddress;

   @Column(name = "total_amount")
   private BigDecimal totalAmount;

   @Column(name = "order_status")
   private String orderStatus;

   public Order() {
   }

   public Order(Customer customer, Restaurant restaurant, String deliveryAddress,
                BigDecimal totalAmount, String orderStatus) {
      this.customer = customer;
      this.restaurant = restaurant;
      this.deliveryAddress = deliveryAddress;
      this.totalAmount = totalAmount;
      this.orderStatus = orderStatus;
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
      Order order = (Order) o;
      return id == order.id && Objects.equals(customer, order.customer) &&
              Objects.equals(restaurant, order.restaurant) && Objects.equals(orderDate, order.orderDate) &&
              Objects.equals(deliveryAddress, order.deliveryAddress) && Objects.equals(totalAmount, order.totalAmount) &&
              Objects.equals(orderStatus, order.orderStatus);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, customer, restaurant, orderDate, deliveryAddress, totalAmount, orderStatus);
   }

   @Override
   public String toString() {
      return "Order{" +
              "id=" + id +
              ", customer=" + customer +
              ", restaurant=" + restaurant +
              ", orderDate=" + orderDate +
              ", deliveryAddress='" + deliveryAddress + '\'' +
              ", totalAmount=" + totalAmount +
              ", orderStatus='" + orderStatus + '\'' +
              '}';
   }
}
