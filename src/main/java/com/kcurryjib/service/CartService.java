package com.kcurryjib.service;

import com.kcurryjib.dto.CartDto;
import com.kcurryjib.mapper.Mappers;
import com.kcurryjib.repo.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

   @Autowired
   private CartRepository cartRepository;

//   @Autowired
   private CartDto cartDto;

   @Autowired
   private Mappers mappers;

   public CartService(Mappers mappers) {
      this.mappers = mappers;
   }
}
