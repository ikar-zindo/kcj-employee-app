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
@RequestMapping("/employee")
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

   // TODO: придумать для чего нужен адрес /employee
   // READ - GET ALL EMPLOYEE ORDERS
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

      return "employee/orders/my-today-orders";
   }

   // READ - GET TODAY EMPLOYEE ORDERS
   @GetMapping("/new-today-orders")
   @PreAuthorize("hasRole('ROLE_USER')")
   public String getNewTodayOrders(Model model) throws EmployeeException {
      List<OrderDto> ordersDto = service.getTodayNewOrders();

      model.addAttribute("today", service.getToday());
      model.addAttribute("orders", ordersDto);

      return "employee/orders/new-today-orders";
   }

   // READ - GET TODAY EMPLOYEE ORDERS
   @GetMapping("/my-today-orders")
   @PreAuthorize("hasRole('ROLE_USER')")
   public String getEmployeeTodayOrders(Model model) throws EmployeeException {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String currentPrincipalName = authentication.getName();

      Employee employee = (Employee) employeeService.loadUserByUsername(currentPrincipalName);

      List<OrderDto> ordersDto = service.getEmployeeProgressingTodayOrders(employee.getId());

      model.addAttribute("orders", ordersDto);
      model.addAttribute("today", service.getToday());

      return "employee/orders/my-today-orders";
   }

   // READ - GET TODAY EMPLOYEE ORDERS
   @GetMapping("/my-history-orders")
   @PreAuthorize("hasRole('ROLE_USER')")
   public String getEmployeeHistoryOrders(Model model) throws EmployeeException {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String currentPrincipalName = authentication.getName();

      Employee employee = (Employee) employeeService.loadUserByUsername(currentPrincipalName);

      List<OrderDto> ordersDto = service.getEmployeeCompletedOrders(employee.getId());

      model.addAttribute("today", service.getToday());
      model.addAttribute("orders", ordersDto);

      return "employee/orders/my-history-orders";
   }

   // UPDATE - CREATED
   @PatchMapping("/order/{id}/created")
   public String createdOrder(@PathVariable Long id) throws OrderException {
      service.createdOrderStatus(id);
      return "redirect:/employee/my-today-orders";
   }

   // UPDATE - COMPLETED
   @PatchMapping("/order/{id}/completed")
   public String completedOrder(@PathVariable Long id) throws OrderException {
      service.completedOrderStatus(id);
      return "redirect:/employee/my-today-orders";
   }

   // UPDATE - COOKING
   @PatchMapping("/order/{id}/cooking")
   public String cookingOrder(@PathVariable Long id) throws OrderException {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String currentPrincipalName = authentication.getName();

      Employee employee = (Employee) employeeService.loadUserByUsername(currentPrincipalName);

      service.cookingOrderStatus(employee.getId(), id);
      return "redirect:/employee/my-today-orders";
   }

   // UPDATE - DELIVERING
   @PatchMapping("/order/{id}/delivering")
   public String deliveringOrder(@PathVariable Long id) throws OrderException {
      service.deliveringOrderStatus(id);
      return "redirect:/employee/my-today-orders";
   }

   // UPDATE - PROCESSING
   @PatchMapping("/order/{id}/cancelled")
   public String cancelledOrder(@PathVariable Long id) throws OrderException {
      service.cancelledOrderStatus(id);
      return "redirect:/employee/my-today-orders";
   }
}
