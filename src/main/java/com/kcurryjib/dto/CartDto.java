package com.kcurryjib.dto;

import com.kcurryjib.entity.CartProduct;
import com.kcurryjib.entity.Customer;

import java.util.List;

public class CartDto {

   private long id;

   private CustomerDto customerDto;

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
