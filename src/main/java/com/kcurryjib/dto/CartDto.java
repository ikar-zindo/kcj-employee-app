package com.kcurryjib.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kcurryjib.entity.CartProduct;
import com.kcurryjib.entity.Customer;

import java.util.List;

public class CartDto {

   private long id;

   @JsonProperty("customer")
   private CustomerDto customerDto;

   @JsonProperty("customerProducts")
   private List<CartProductDto> cartProductsDto;

   public CartDto() {
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
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
}
