package com.kcurryjib.controller.customer;

import com.kcurryjib.dto.ProductDto;
import com.kcurryjib.service.admin.RestaurantService;
import com.kcurryjib.service.customer.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/menu")
@SessionAttributes("editMenu")
public class MenuController {

   private final
   MenuService service;

   private final RestaurantService restaurantService;

   @Autowired
   public MenuController(MenuService service,
                         RestaurantService restaurantService) {

      this.service = service;
      this.restaurantService = restaurantService;
   }

   @GetMapping
   @PreAuthorize("hasRole('ROLE_USER')")
   public String getAll(Model model) {
      List<ProductDto> productsDto = service.getAvailableProducts();

      model.addAttribute("products", productsDto);

      return "customer/menu";
   }
}
