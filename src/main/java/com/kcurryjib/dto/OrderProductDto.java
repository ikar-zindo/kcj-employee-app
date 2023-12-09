package com.kcurryjib.dto;

import com.kcurryjib.entity.Order;
import com.kcurryjib.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class OrderProductDto {

   private long id;
   private Order order;
   private Product product;
   private int quantity;
   private BigDecimal total;
   private LocalDateTime cratedAt;

   public OrderProductDto() {
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      OrderProductDto that = (OrderProductDto) o;
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
      return "OrderProductDto{" +
              "id=" + id +
              ", order=" + order +
              ", product=" + product +
              ", quantity=" + quantity +
              ", total=" + total +
              ", cratedAt=" + cratedAt +
              '}';
   }
}
