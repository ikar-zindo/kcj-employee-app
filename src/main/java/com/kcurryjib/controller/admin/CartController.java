package com.kcurryjib.controller.admin;

import com.kcurryjib.dto.CartDto;
import com.kcurryjib.service.admin.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/cart")
public class CartController {

   @Autowired
   private CartService cartService;

   @GetMapping
   public ResponseEntity<List<CartDto>> getAll() {
      List<CartDto> cartsDto = cartService.gatAll();

      return new ResponseEntity<>(cartsDto, HttpStatus.OK);
   }
}