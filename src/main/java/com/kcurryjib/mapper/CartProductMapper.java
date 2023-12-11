package com.kcurryjib.mapper;

import com.kcurryjib.dto.CartProductDto;
import com.kcurryjib.entity.CartProduct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartProductMapper {

   @Autowired
   private ModelMapper mapper;

   public CartProductDto convertToCartProductDto(CartProduct cartProduct) {
      CartProductDto cartProductDto = mapper.map(cartProduct, CartProductDto.class);

      return cartProductDto;
   }

   public List<CartProductDto> convertToCartProductsDto(List<CartProduct> cartProducts) {
      return cartProducts.stream()
              .map(this::convertToCartProductDto)
              .collect(Collectors.toList());
   }
}
