package com.kcurryjib.controller.admin;

import com.kcurryjib.dto.RestaurantDto;
import com.kcurryjib.service.admin.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class Dashboard {

   @Autowired
   private RestaurantService service;

   @GetMapping("/dashboard")
   public String fullEmployeeReview(Model model) {
      List<RestaurantDto> restaurantsDto = service.fullInfo();

      model.addAttribute("restaurants", restaurantsDto);

      return "/admin/dashboard";
   }
}
