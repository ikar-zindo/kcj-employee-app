package com.kcurryjib.controller.employee;

import com.kcurryjib.dto.EmployeeDto;
import com.kcurryjib.dto.OrderDto;
import com.kcurryjib.entity.Employee;
import com.kcurryjib.exception.list.EmployeeException;
import com.kcurryjib.exception.list.OrderException;
import com.kcurryjib.service.admin.EmployeeService;
import com.kcurryjib.service.employee.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee/orders")
@SessionAttributes("orders")
@PreAuthorize("hasRole('ROLE_USER')")
public class OrderController {

   private final EmployeeService employeeService;

   private final OrderService service;

   @Autowired
   public OrderController(OrderService service,
                          EmployeeService employeeService) throws OrderException {

      this.service = service;
      this.employeeService = employeeService;
   }

   // READ
   @GetMapping()
   @PreAuthorize("hasRole('ROLE_USER')")
   public String getEmployeeOrders(Model model) throws EmployeeException {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String currentPrincipalName = authentication.getName();


      Employee employee = (Employee) employeeService.loadUserByUsername(currentPrincipalName);

      EmployeeDto employeeDto = service.getEmployeeWithOrders(employee.getId());
      List<OrderDto> ordersDto = employeeDto.getOrdersDto();

      model.addAttribute("orders", ordersDto);
      model.addAttribute("today", service.getToday());

      return "/employee/orders/list";
   }

   // READ - GET TODAY EMPLOYEE ORDERS
   @GetMapping("/today")
   @PreAuthorize("hasRole('ROLE_USER')")
   public String getTodayOrders(Model model) throws EmployeeException {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String currentPrincipalName = authentication.getName();

      Employee employee = (Employee) employeeService.loadUserByUsername(currentPrincipalName);

      List<OrderDto> ordersDto = service.getTodayOrders(employee.getId());

      model.addAttribute("orders", ordersDto);

      return "/employee/orders/list";
   }

   // UPDATE - CREATED
   @PatchMapping("/{id}/created")
   public String createdOrder(@PathVariable Long id) throws OrderException {
      service.createdOrderStatus(id);
      return "redirect:/employee/orders";
   }

   // UPDATE - COMPLETED
   @PatchMapping("/{id}/completed")
   public String completedOrder(@PathVariable Long id) throws OrderException {
      service.completedOrderStatus(id);
      return "redirect:/employee/orders";
   }

   // UPDATE - COOKING
   @PatchMapping("/{id}/cooking")
   public String cookingOrder(@PathVariable Long id) throws OrderException {
      service.cookingOrderStatus(id);
      return "redirect:/employee/orders";
   }

   // UPDATE - DELIVERING
   @PatchMapping("/{id}/delivering")
   public String deliveringOrder(@PathVariable Long id) throws OrderException {
      service.deliveringOrderStatus(id);
      return "redirect:/employee/orders";
   }

   // UPDATE - PROCESSING
   @PatchMapping("/{id}/cancelled")
   public String cancelledOrder(@PathVariable Long id) throws OrderException {
      service.cancelledOrderStatus(id);
      return "redirect:/employee/orders";
   }
}
