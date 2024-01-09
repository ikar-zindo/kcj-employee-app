package com.kcurryjib.controller.admin;

import com.kcurryjib.dto.EmployeeDto;
import com.kcurryjib.exception.list.EmployeeException;
import com.kcurryjib.service.admin.EmployeeService;
import com.kcurryjib.service.admin.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/employees")
@SessionAttributes("editEmployees")
public class EmployeeController {

   private final EmployeeService service;

   private final RestaurantService restaurantService;

   @Autowired
   public EmployeeController(EmployeeService service,
                             RestaurantService restaurantService) {

      this.service = service;
      this.restaurantService = restaurantService;
   }

   // READ
   @GetMapping()
   public String getAllEmployees(Model model) throws EmployeeException {
      List<EmployeeDto> employeesDto = service.getAll();

      model.addAttribute("employees", employeesDto);

      return "/admin/employees/list";
   }
}
