package com.kcurryjib.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

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

   @ManyToOne(fetch = FetchType.LAZY)
   private Customer customer;

   public OrderProduct() {
   }

   public OrderProduct(Order order, Product product, int quantity, BigDecimal total) {
      this.order = order;
      this.product = product;
      this.quantity = quantity;
      this.total = total;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public Order getOrder() {
      return order;
   }

   public void setOrder(Order order) {
      this.order = order;
   }

   public Product getProduct() {
      return product;
   }

   public void setProduct(Product product) {
      this.product = product;
   }

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public BigDecimal getTotal() {
      return total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
   }

   public LocalDateTime getCratedAt() {
      return cratedAt;
   }

   public void setCratedAt(LocalDateTime cratedAt) {
      this.cratedAt = cratedAt;
   }

   public Customer getCustomer() {
      return customer;
   }

   public void setCustomer(Customer customer) {
      this.customer = customer;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      OrderProduct that = (OrderProduct) o;
      return id == that.id && quantity == that.quantity && Objects.equals(order, that.order) &&
              Objects.equals(product, that.product) && Objects.equals(total, that.total) &&
              Objects.equals(cratedAt, that.cratedAt);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, order, product, quantity, total, cratedAt);
   }

   @Override
   public String toString() {
      return "OrderProduct{" +
              "id=" + id +
              ", order=" + order +
              ", product=" + product +
              ", quantity=" + quantity +
              ", total=" + total +
              ", cratedAt=" + cratedAt +
              '}';
   }
}
