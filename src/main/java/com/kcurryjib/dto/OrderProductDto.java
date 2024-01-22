package com.kcurryjib.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kcurryjib.entity.Review;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;


public class OrderProductDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Long id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private int quantity;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private BigDecimal total;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private LocalDateTime cratedAt;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("order")
   private OrderDto orderDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("product")
   private ProductDto productDto;

   public OrderProductDto() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
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

   public OrderDto getOrderDto() {
      return orderDto;
   }

   public void setOrderDto(OrderDto orderDto) {
      this.orderDto = orderDto;
   }

   public ProductDto getProductDto() {
      return productDto;
   }

   public void setProductDto(ProductDto productDto) {
      this.productDto = productDto;
   }

   // Builder class
   public static class Builder {

      private OrderProductDto orderProductDto = new OrderProductDto();

      public Builder id(Long id) {
         orderProductDto.id = id;
         return this;
      }

      public Builder quantity(int quantity) {
         orderProductDto.quantity = quantity;
         return this;
      }

      public Builder total(BigDecimal total) {
         orderProductDto.total = total;
         return this;
      }

      public Builder cratedAt(LocalDateTime cratedAt) {
         orderProductDto.cratedAt = cratedAt;
         return this;
      }

      public Builder orderDto(OrderDto orderDto) {
         orderProductDto.orderDto = orderDto;
         return this;
      }

      public Builder productDto(ProductDto productDto) {
         orderProductDto.productDto = productDto;
         return this;
      }

      public OrderProductDto build() {
         return orderProductDto;
      }
   }

   public static Review.Builder builder() {
      return new Review.Builder();
   }

   // ToString

   @Override
   public String toString() {
      return "OrderProductDto{" +
              "id=" + id +
              ", quantity=" + quantity +
              ", total=" + total +
              ", cratedAt=" + cratedAt +
              ", orderDto=" + orderDto +
              ", productDto=" + productDto +
              '}';
   }

   // Equals & HashCode

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      OrderProductDto that = (OrderProductDto) o;
      return quantity == that.quantity && Objects.equals(id, that.id) && Objects.equals(total, that.total) &&
              Objects.equals(cratedAt, that.cratedAt) && Objects.equals(orderDto, that.orderDto) &&
              Objects.equals(productDto, that.productDto);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, quantity, total, cratedAt, orderDto, productDto);
   }
}


