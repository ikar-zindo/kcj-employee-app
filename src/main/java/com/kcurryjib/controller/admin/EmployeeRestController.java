package com.kcurryjib.controller.admin;

import com.kcurryjib.dto.EmployeeDto;
import com.kcurryjib.service.admin.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/employees")
public class EmployeeRestController {

   @Autowired
   private EmployeeService service;

   // ALL EMPLOYEES
   @GetMapping("/all")
   public ResponseEntity<List<EmployeeDto>> getAll() {
      List<EmployeeDto> employees = service.getAll();

      return new ResponseEntity<>(employees, HttpStatus.OK);
   }

   @PostMapping("/add")
   public ResponseEntity<List<EmployeeDto>> createEmployee() {
      List<EmployeeDto> employees = service.getAll();

      return new ResponseEntity<>(employees, HttpStatus.OK);
   }
}