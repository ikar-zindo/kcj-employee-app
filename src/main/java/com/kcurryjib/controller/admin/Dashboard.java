package com.kcurryjib.controller.admin;

import com.kcurryjib.dto.RestaurantDto;
import com.kcurryjib.service.admin.AdminOrderService;
import com.kcurryjib.service.admin.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class Dashboard {

   private final RestaurantService service;

   private final AdminOrderService adminOrderService;

   @Autowired
   public Dashboard(RestaurantService service,
                    AdminOrderService adminOrderService) {

      this.service = service;
      this.adminOrderService = adminOrderService;
   }

   @GetMapping("/dashboard")
   public String fullEmployeeReview(Model model) {
      List<RestaurantDto> restaurantsDto = service.fullInfo();
      Map<DayOfWeek, Integer> ordersByDayOfWeek = adminOrderService.calculateOrdersByDayOfWeekForLastWeek();

      model.addAttribute("ordersByDayOfWeek", ordersByDayOfWeek);
      model.addAttribute("restaurants", restaurantsDto);

      return "/admin/dashboard";
   }
}
