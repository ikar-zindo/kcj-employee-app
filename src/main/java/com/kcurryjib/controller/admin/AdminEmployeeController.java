package com.kcurryjib.controller.admin;

import com.kcurryjib.dto.EmployeeDto;
import com.kcurryjib.dto.OrderDto;
import com.kcurryjib.dto.RestaurantDto;
import com.kcurryjib.entity.Employee;
import com.kcurryjib.exception.list.EmployeeException;
import com.kcurryjib.service.admin.EmployeeService;
import com.kcurryjib.service.admin.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/employees")
@SessionAttributes("editEmployees")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
public class AdminEmployeeController {

   private final EmployeeService service;

   private final RestaurantService restaurantService;

   @Autowired
   public AdminEmployeeController(EmployeeService service,
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

   // READ
   @GetMapping("/{id}")
   public String getEmployeeById(@PathVariable Long id,
                                Model model) throws EmployeeException {

      EmployeeDto employeeDto = service.getEmployeeById(id);

      model.addAttribute("employee", employeeDto);

      return "/admin/employees/info";
   }

   // READ
   @GetMapping("/orders")
   @PreAuthorize("hasRole('ROLE_USER')")
   public String getEmployeeOrders(Model model) {

      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String currentPrincipalName = authentication.getName();

      Employee employee = (Employee) service.loadUserByUsername(currentPrincipalName);

      EmployeeDto employeeDto = service.getEmployeeWithOrders(employee.getId());
      List<OrderDto> ordersDto = employeeDto.getOrdersDto();

      model.addAttribute("employee", employeeDto);
      model.addAttribute("orders", ordersDto);

      return "/employee/orders/list";
   }

   // CREATE
   @GetMapping(value = "/add")
   @PreAuthorize("hasRole('ROLE_ADMIN')")
   public String addEmployee(@ModelAttribute("employee") EmployeeDto employeeDto,
                            Model model) throws EmployeeException {

      model.addAttribute("restaurants", restaurantService.getAll());

      return "/admin/employees/add";
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

         return "/admin/employees/add";
      }

      service.addEmployee(employeeDto);

      return "redirect:/admin/employees";
   }

   // UPDATE
   @GetMapping(value = "/{id}/edit")
   public String editEmployee(@PathVariable(value = "id") Long id,
                             Model model) throws EmployeeException {

      EmployeeDto employeeDto = service.getEmployeeById(id);

      if (employeeDto == null) {
         return "redirect:/admin/employees";
      }

      model.addAttribute("employee", employeeDto);
      model.addAttribute("restaurants", restaurantService.getAll());

      return "/admin/employees/edit";
   }

   // todo: реализовать обновление информации о сотруднике без учёта пароля
   // UPDATE
   @PostMapping(value = "/{id}/edit")
   public String updateEmployee(@ModelAttribute("employee") @Valid EmployeeDto employeeDto,
                               BindingResult result,
                               @RequestParam(name = "restaurantId") Long restaurantId,
                               Model model) throws EmployeeException {

      RestaurantDto restaurantDto = restaurantService.getById(restaurantId);

      employeeDto.setRestaurantDto(restaurantDto);

      if (result.hasErrors()) {
         model.addAttribute("employee", employeeDto);
         model.addAttribute("restaurants", restaurantService.getAll());

         return "/admin/employees/edit";
      }

//      employeeDto.setPassword(employeeDto.getPassword());

      service.updateEmployee(employeeDto);
      return "redirect:/admin/employees";
   }

   // DELETE
   @PostMapping("/{id}/block")
   public String blockEmployee(@PathVariable Long id) throws EmployeeException {
      service.blockEmployee(id);
      return "redirect:/admin/employees";
   }
}
