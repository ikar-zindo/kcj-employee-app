package com.kcurryjib.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "customer")
public class Customer {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "customer_id")
   private long id;

   @Column(name = "name")
   private String name;

   @Column(name = "email")
   private String email;

   @Column(name = "phone_number")
   private String phoneNumber;

   @Column(name = "address")
   private String address;

   @Column(name = "password")
   private String password;

   @Column(name = "created_at")
   private Timestamp createdAt;

   @Column(name = "is_blocked")
   private boolean isBlocked;

   @OneToOne(mappedBy = "customer")
   private Cart cart;

   public Customer() {
   }

   public Customer(String name, String email, String phoneNumber,
                   String address, String password) {
      this.name = name;
      this.email = email;
      this.phoneNumber = phoneNumber;
      this.address = address;
      this.password = password;

      createdAt = Timestamp.from(Instant.now());
      this.isBlocked = false;
   }
}
