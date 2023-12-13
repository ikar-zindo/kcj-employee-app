package com.kcurryjib.controller.admin;

import com.kcurryjib.dto.CustomerDto;
import com.kcurryjib.service.admin.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/customer")
public class CustomerController {

   @Autowired
   private CustomerService customerService;

   @GetMapping
   public ResponseEntity<List<CustomerDto>> gatAll() {
      List<CustomerDto> customersDto = customerService.getAll();

      return new ResponseEntity<>(customersDto, HttpStatus.OK);
   }
}
