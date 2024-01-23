package com.kcurryjib.controller.admin;

import com.kcurryjib.dto.ProductDto;
import com.kcurryjib.service.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/menu")
public class AdminMenuController {

   @Autowired
   private ProductService service;

   @GetMapping
   public String getAll(Model model) {
      List<ProductDto> productsDto = service.getAvailableProducts();

      model.addAttribute("products", productsDto);

      return "/admin/menu/list";
   }
}
