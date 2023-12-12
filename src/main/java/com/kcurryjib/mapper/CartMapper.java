package com.kcurryjib.mapper;

import com.kcurryjib.dto.CartDto;
import com.kcurryjib.entity.Cart;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

   @Autowired
   private ModelMapper mapper;

   // convert to DTO
   public CartDto convertToCartDto(Cart cart) {
      CartDto cartDto = mapper.map(cart, CartDto.class);

      return cartDto;
   }

   // convert to entity
   public Cart convertTocart(CartDto cartDto) {
      Cart cart = mapper.map(cartDto, Cart.class);

      return cart;
   }
}
