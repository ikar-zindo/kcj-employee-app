package com.kcurryjib.controller;

import com.kcurryjib.dto.OrderProductDto;
import com.kcurryjib.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order-product")
public class OrderProductController {

   @Autowired
   private OrderProductService orderProductService;

   @GetMapping
   public ResponseEntity<List<OrderProductDto>> getAll() {
      List<OrderProductDto> orderProductsDto = orderProductService.getAll();

      return new ResponseEntity<>(orderProductsDto, HttpStatus.OK);
   }
}
