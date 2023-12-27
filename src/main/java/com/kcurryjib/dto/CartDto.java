package com.kcurryjib.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kcurryjib.entity.CartProduct;
import com.kcurryjib.entity.Customer;

import java.util.List;
import java.util.Objects;

public class CartDto {
   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Long id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("customer")
   private CustomerDto customerDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("customerProducts")
   private List<CartProductDto> cartProductsDto;

   public CartDto() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public CustomerDto getCustomerDto() {
      return customerDto;
   }

   public void setCustomerDto(CustomerDto customerDto) {
      this.customerDto = customerDto;
   }

   public List<CartProductDto> getCartProductsDto() {
      return cartProductsDto;
   }

   public void setCartProductsDto(List<CartProductDto> cartProductsDto) {
      this.cartProductsDto = cartProductsDto;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      CartDto cartDto = (CartDto) o;
      return Objects.equals(id, cartDto.id) && Objects.equals(customerDto, cartDto.customerDto) &&
              Objects.equals(cartProductsDto, cartDto.cartProductsDto);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, customerDto, cartProductsDto);
   }

   @Override
   public String toString() {
      return "CartDto{" +
              "id=" + id +
              ", customerDto=" + customerDto +
              ", cartProductsDto=" + cartProductsDto +
              '}';
   }
}
