package com.kcurryjib.service;

import com.kcurryjib.config.MapperUtil;
import com.kcurryjib.dto.CartDto;
import com.kcurryjib.entity.Cart;
import com.kcurryjib.mapper.CartMapper;
import com.kcurryjib.repo.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

   @Autowired
   private CartRepository cartRepository;

   @Autowired
   private CartMapper cartMapper;

   public List<CartDto> gatAll() {
      List<Cart> carts = new ArrayList<>(cartRepository.findAll());

      return MapperUtil.convertlist(carts, cartMapper::convertToCartDto);
   }
}
