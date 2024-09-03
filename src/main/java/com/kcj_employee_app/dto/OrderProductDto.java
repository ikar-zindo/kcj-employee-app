package com.kcj_employee_app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.UUID;


public class OrderProductDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private UUID id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private int quantity;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("order")
   private OrderDto orderDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("product")
   private ProductDto productDto;

   public OrderProductDto() {
   }

   public UUID getId() {
      return id;
   }

   public void setId(UUID id) {
      this.id = id;
   }

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
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

      public Builder id(UUID id) {
         orderProductDto.id = id;
         return this;
      }

      public Builder quantity(int quantity) {
         orderProductDto.quantity = quantity;
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

   public static Builder builder() {
      return new Builder();
   }

   // ToString
   @Override
   public String toString() {
      return "OrderProductDto{" +
              "id=" + id +
              ", quantity=" + quantity +
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
      return quantity == that.quantity && Objects.equals(id, that.id) &&
              Objects.equals(orderDto, that.orderDto) && Objects.equals(productDto, that.productDto);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, quantity, orderDto, productDto);
   }
}


