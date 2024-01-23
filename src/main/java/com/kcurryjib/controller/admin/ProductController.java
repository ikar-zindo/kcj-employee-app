package com.kcurryjib.controller.admin;

import com.kcurryjib.dto.ProductDto;
import com.kcurryjib.dto.RestaurantDto;
import com.kcurryjib.exception.list.ProductException;
import com.kcurryjib.service.admin.ProductService;
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
@RequestMapping("/admin/products")
@SessionAttributes("editProducts")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_USER')")
public class ProductController {

   private final ProductService service;

   private final RestaurantService restaurantService;

   @Autowired
   public ProductController(ProductService service,
                            RestaurantService restaurantService) {

      this.service = service;
      this.restaurantService = restaurantService;
   }

   // READ
   @GetMapping
   public String getAllProducts(Model model) throws ProductException {
      List<ProductDto> productsDto = service.getAll();

      model.addAttribute("products", productsDto);

      return "/admin/products/all";
   }

   // READ
   @GetMapping("/{id}")
   public String getProductById(@PathVariable Long id,
                                Model model) throws ProductException {

      if (service.getProductById(id) == null) {
         return "redirect:/admin/products";
      }

      ProductDto productDto = service.getProductById(id);

      model.addAttribute("product", productDto);

      return "/admin/products/info";
   }

   // CREATE
   @GetMapping(value = "/add")
   @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
   public String addProduct(@ModelAttribute("product") ProductDto productDto,
                            Model model) throws ProductException {

      productDto.setImageUrl("1.jpg");
      productDto.setName("new product");
      model.addAttribute("restaurants", restaurantService.getAll());

      return "/admin/products/add";
   }

   // CREATE
   @PostMapping(value = "/add")
   @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
   public String createProduct(@ModelAttribute("product") @Valid ProductDto productDto,
                               BindingResult result,
                               @RequestParam(name = "restaurantId") Long restaurantId,
                               Model model) throws ProductException {

      RestaurantDto restaurantDto = restaurantService.getById(restaurantId);
      productDto.setRestaurantDto(restaurantDto);

      if (result.hasErrors()) {
         model.addAttribute("product", productDto);
         model.addAttribute("restaurants", restaurantService.getAll());

         return "/admin/products/add";
      }

      service.addProduct(productDto);
      return "redirect:/admin/products";
   }

   // UPDATE
   @GetMapping(value = "/{id}/edit")
   @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
   public String editProduct(@PathVariable(value = "id") Long id,
//                             @ModelAttribute("product") ProductDto productDto,
                             Model model) throws ProductException {

      ProductDto productDto = service.getProductById(id);

      if (productDto == null) {
         return "redirect:/admin/products";
      }

      model.addAttribute("product", productDto);
      model.addAttribute("restaurants", restaurantService.getAll());

      return "/admin/products/edit";
   }

   // UPDATE
   @PostMapping(value = "/{id}/edit")
   @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_USER')")
   public String updateProduct(@ModelAttribute("product") @Valid ProductDto productDto,
                               BindingResult result,
                               @RequestParam(name = "restaurantId") Long restaurantId,
                               Model model) throws ProductException {

      RestaurantDto restaurantDto = restaurantService.getById(restaurantId);

      productDto.setRestaurantDto(restaurantDto);

      if (result.hasErrors()) {
         model.addAttribute("product", productDto);
         model.addAttribute("restaurants", restaurantService.getAll());
         return "/admin/products/edit";
      }

      service.updateProduct(productDto);
      return "redirect:/admin/products";
   }

   // UPDATE
   @PostMapping("/{id}/block")
//   @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_USER')")
   public String blockProduct(@PathVariable Long id) throws ProductException {
      service.blockProduct(id);
      return "redirect:/admin/products";
   }

   // UPDATE
   @PostMapping("/{id}/unblock")
//   @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_USER')")
   public String unblockProduct(@PathVariable Long id) throws ProductException {
      service.unblockProduct(id);
      return "redirect:/admin/products";
   }
}
