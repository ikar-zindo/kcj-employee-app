package com.kcurryjib.controller.admin;

import com.kcurryjib.dto.EmployeeDto;
import com.kcurryjib.entity.Employee;
import com.kcurryjib.service.admin.EmployeeService;
import com.kcurryjib.service.admin.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeRestController {

   @Autowired
   private EmployeeService service;

   // ALL EMPLOYEES
   @GetMapping("/all")
   public ResponseEntity<List<EmployeeDto>> getAll() {
      List<EmployeeDto> employees = service.getAll();

      return new ResponseEntity<>(employees, HttpStatus.OK);
   }

    // ALL ROlES
   @GetMapping("/name/{username}")
   public UserDetails getByUsername(@PathVariable String username) {
      return service.loadUserByUsername(username);
   }

   // ONLY ROLE_ADMIN
   @PostMapping("/save")
   public Employee save(@RequestBody Employee employee) {
      return service.save(employee);
   }
}
