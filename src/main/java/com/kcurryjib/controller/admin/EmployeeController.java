package com.kcurryjib.controller.admin;

import com.kcurryjib.dto.EmployeeDto;
import com.kcurryjib.dto.RestaurantDto;
import com.kcurryjib.exception.list.EmployeeException;
import com.kcurryjib.service.admin.EmployeeService;
import com.kcurryjib.service.admin.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/employees")
@SessionAttributes("editEmployees")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
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

      return "admin/employees/list";
   }

   // READ
   @GetMapping("/{id}")
   public String getEmployeeById(@PathVariable Long id,
                                 Model model) throws EmployeeException {

      EmployeeDto employeeDto = service.getEmployeeById(id);

      model.addAttribute("employee", employeeDto);

      return "admin/employees/info";
   }

   // CREATE
   @GetMapping(value = "/add")
   @PreAuthorize("hasRole('ROLE_ADMIN')")
   public String addEmployee(@ModelAttribute("employee") EmployeeDto employeeDto,
                             Model model) throws EmployeeException {

      model.addAttribute("restaurants", restaurantService.getAll());

      return "admin/employees/add";
   }

   // CREATE
   @PostMapping(value = "/add")
   @PreAuthorize("hasRole('ROLE_ADMIN')")
   public String createEmployee(@ModelAttribute("employee") @Valid EmployeeDto employeeDto,
                                BindingResult result,
                                @RequestParam(name = "restaurantId") Long restaurantId,
                                Model model) throws EmployeeException {

      RestaurantDto restaurantDto = restaurantService.getById(restaurantId);
      employeeDto.setRestaurantDto(restaurantDto);

      if (result.hasErrors()) {
         model.addAttribute("employee", employeeDto);
         model.addAttribute("restaurants", restaurantService.getAll());

         return "admin/employees/add";
      }

      service.addEmployee(employeeDto);
      return "redirect:/admin/employees";
   }

   // UPDATE
   @GetMapping(value = "/{id}/edit")
   @PreAuthorize("hasRole('ROLE_ADMIN')")
   public String editEmployee(@PathVariable(value = "id") Long id,
                              Model model) throws EmployeeException {

      if (service.getEmployeeById(id) == null) {
         return "redirect:/admin/employees";
      }

      EmployeeDto employeeDto = service.getEmployeeById(id);

      model.addAttribute("employee", employeeDto);
      model.addAttribute("restaurants", restaurantService.getAll());

      return "admin/employees/edit";
   }

   // todo: реализовать обновление информации о сотруднике без учёта пароля
   // UPDATE
   @PatchMapping(value = "/{id}/edit")
   @PreAuthorize("hasRole('ROLE_ADMIN')")
   public String updateEmployee(@ModelAttribute("employee") @Valid EmployeeDto employeeDto,
                                BindingResult result,
                                @RequestParam(name = "restaurantId") Long restaurantId,
                                Model model) throws EmployeeException {

      RestaurantDto restaurantDto = restaurantService.getById(restaurantId);

      employeeDto.setRestaurantDto(restaurantDto);

      if (result.hasErrors()) {
         model.addAttribute("employee", employeeDto);
         model.addAttribute("restaurants", restaurantService.getAll());

         return "admin/employees/edit";
      }

      service.updateEmployee(employeeDto);
      return "redirect:/admin/employees";
   }

   // DELETE
   @PatchMapping("/{id}/block")
   public String blockEmployee(@PathVariable Long id) throws EmployeeException {
      service.blockEmployee(id);
      return "redirect:/admin/employees";
   }
}
