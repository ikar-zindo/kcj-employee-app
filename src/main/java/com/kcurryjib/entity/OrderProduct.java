package com.kcurryjib.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_product")
public class OrderProduct {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "order_product_id")
   private long id;

   @ManyToOne
   @JoinColumn(name = "order_id")
   private Order order;

   @ManyToOne
   @JoinColumn(name = "product_id")
   private Product product;

   @Column(name = "quantity")
   private int quantity;

   @Column(name = "total")
   private BigDecimal total;

   @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
   private LocalDateTime cratedAt;

   public OrderProduct() {
   }

   public OrderProduct(Order order, Product product, int quantity, BigDecimal total) {
      this.order = order;
      this.product = product;
      this.quantity = quantity;
      this.total = total;
   }
}
