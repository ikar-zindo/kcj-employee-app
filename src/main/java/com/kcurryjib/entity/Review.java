package com.kcurryjib.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "review")
public class Review {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "review_id")
   private long id;

   @OneToOne
   @JoinColumn(name = "restaurant_id")
   private Restaurant restaurant;

   @ManyToOne
   @JoinColumn(name = "customer_id")
   private Customer customer;

   @Column(name = "rating")
   private BigDecimal rating;

   @Column(name = "comment")
   private String comment;

   @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
   private LocalDateTime createdAt;
}
