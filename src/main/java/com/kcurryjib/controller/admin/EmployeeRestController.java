package com.kcurryjib.controller.admin;

import com.kcurryjib.dto.EmployeeDto;
import com.kcurryjib.entity.Employee;
import com.kcurryjib.service.admin.EmployeeService;
import com.kcurryjib.service.admin.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
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
   public List<EmployeeDto> getAll() {
      return service.getAll();
   }

   // ALL ROlES
   @GetMapping("/name/{nickname}")
   public UserDetails getByNickname(@PathVariable String nickname) {
      return service.loadUserByUsername(nickname);
   }

   // ONLY ROLE_ADMIN
   @PostMapping("/save")
   public Employee save(@RequestBody Employee employee) {
      return service.save(employee);
   }
}
