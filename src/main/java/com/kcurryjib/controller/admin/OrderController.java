package com.kcurryjib.controller.admin;

import com.kcurryjib.dto.OrderDto;
import com.kcurryjib.service.admin.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/order")
public class OrderController {

   @Autowired
   private OrderService orderService;

   @GetMapping
   public ResponseEntity<List<OrderDto>> getAll() {
      List<OrderDto> ordersDto = orderService.getAll();

      return new ResponseEntity<>(ordersDto, HttpStatus.OK);
   }
}
