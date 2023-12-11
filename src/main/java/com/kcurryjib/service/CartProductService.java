package com.kcurryjib.service;

import com.kcurryjib.config.MapperUtil;
import com.kcurryjib.dto.CartProductDto;
import com.kcurryjib.entity.CartProduct;
import com.kcurryjib.mapper.CartProductMapper;
import com.kcurryjib.repo.CartProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartProductService {

   @Autowired
   private CartProductRepository cartProductRepository;

   @Autowired
   private CartProductMapper cartProductMapper;

   public List<CartProductDto> getAll() {
      List<CartProduct> cartProducts = new ArrayList<>(cartProductRepository.findAll());

      return MapperUtil.convertlist(cartProducts, cartProductMapper::convertToCartProductDto);
   }
}
