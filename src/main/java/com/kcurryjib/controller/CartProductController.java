package com.kcurryjib.controller;

import com.kcurryjib.dto.CartProductDto;
import com.kcurryjib.service.CartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart-product")
public class CartProductController {

   @Autowired
   private CartProductService cartProductService;

   @GetMapping
   public ResponseEntity<List<CartProductDto>> getAll() {
      List<CartProductDto> cartProductsDto = cartProductService.getAll();

      return new ResponseEntity<>(cartProductsDto, HttpStatus.OK);
   }
}
