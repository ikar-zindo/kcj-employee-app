package com.kcurryjib.controller.admin;

import com.kcurryjib.dto.OrderDto;
import com.kcurryjib.exception.list.OrderException;
import com.kcurryjib.service.admin.AdminOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/orders")
@SessionAttributes("adminOrders")
public class AdminOrderController {

   private final AdminOrderService service;

   @Autowired
   public AdminOrderController(AdminOrderService service) {
      this.service = service;
   }

   // RAED
   @GetMapping
   public String getAllOrders(Model model) throws OrderException {
      List<OrderDto> ordersDto = service.getAll();

      model.addAttribute("orders", ordersDto);

      return "admin/orders/all";
   }
}
