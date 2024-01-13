package com.kcurryjib.controller.employee;

import com.kcurryjib.dto.EmployeeDto;
import com.kcurryjib.dto.OrderDto;
import com.kcurryjib.service.admin.EmployeeService;
import com.kcurryjib.service.employee.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@Controller
@RequestMapping("/employees")
public class OrderController {


   private final OrderService orderService;

   @Autowired
   public OrderController(OrderService orderService) {
      this.orderService = orderService;
   }

   @GetMapping("/all")
   public ResponseEntity<List<EmployeeDto>> getAll() {
      List<EmployeeDto> employees = orderService.getAllActiveEmployee();

      return new ResponseEntity<>(employees, HttpStatus.OK);
   }

   @GetMapping("/orders")
   public ResponseEntity<List<OrderDto>> getAllOrders() {
      List<OrderDto> orders = orderService.getAllOrders();

      return new ResponseEntity<>(orders, HttpStatus.OK);
   }
}
