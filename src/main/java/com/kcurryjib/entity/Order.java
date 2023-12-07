package com.kcurryjib.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
}
