package com.kcurryjib.controller.admin;

import com.kcurryjib.dto.ProductDto;
import com.kcurryjib.dto.RestaurantDto;
import com.kcurryjib.exceptions.ProductException;
import com.kcurryjib.service.admin.ProductService;
import com.kcurryjib.service.admin.RestaurantService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

   @Autowired
   private ProductService service;

   @Autowired
   private RestaurantService restaurantService;

   // READ
   @GetMapping
   public String getAllProducts(Model model) {
      List<ProductDto> productsDto = service.getAll();

      model.addAttribute("products", productsDto);

      return "/admin/products/all";
   }

   // READ
   @GetMapping("/{id}")
   public String getProductById(@PathVariable Long id, Model model) {
      ProductDto product = service.getProductById(id);
      model.addAttribute("product", product);
      return "/admin/products/info";
   }

   // CREATE
   @GetMapping("/add")
   public String addProductForm(Model model) throws ProductException {
      Iterable<RestaurantDto> restaurantsDto = restaurantService.getAll();

      model.addAttribute("restaurants", restaurantsDto);

      return "/admin/products/add";
   }

   // CREATE
   @PostMapping("/add")
   public String addProduct(@RequestParam @Valid String name,
                            @RequestParam @Valid String description,
                            @RequestParam @Valid BigDecimal price,
                            @RequestParam String imageUrl,
                            @RequestParam(name = "available", required = false,
                                    defaultValue = "false") boolean available,
                            @RequestParam Long restaurantId,
                            Model model) throws ProductException {

      ProductDto productDto = new ProductDto();

      productDto.setName(name);
      productDto.setDescription(description);
      productDto.setPrice(price);
      productDto.setImageUrl(imageUrl);
      productDto.setAvailable(available);
      productDto.setRestaurantDto(restaurantService.getById(restaurantId));

      service.addProduct(productDto);

      return "redirect:/admin/products";
   }

//   @PostMapping("/add")
//   public String addProduct(@Valid @ModelAttribute("product") ProductDto productDto,
//                            BindingResult result,
//                            Model model) throws ProductException {
//
//      if (result.hasErrors()) {
//         return "redirect:/admin/products"; // Здесь вы можете указать представление для отображения ошибок
//      }
//
//      service.addProduct(productDto);
//
//      return "redirect:/admin/products";
//   }

   // UPDATE
   @GetMapping("/{id}/edit")
   public String editProduct(@PathVariable(value = "id") Long id, Model model) throws ProductException {
      if (service.getProductById(id) == null) {
         return "redirect:/admin/products";
      }

      ProductDto productDto = service.getProductById(id);
      Iterable<RestaurantDto> restaurantsDto = restaurantService.getAll();

      model.addAttribute("product", productDto);
      model.addAttribute("restaurants", restaurantsDto);

      return "/admin/products/edit";
   }

   // UPDATE
   @PostMapping("/{id}/edit")
   public String updateProduct(@PathVariable(value = "id") Long id,
                               @RequestParam String name,
                               @RequestParam String description,
                               @RequestParam BigDecimal price,
                               @RequestParam String imageUrl,
                               @RequestParam(name = "available", required = false,
                                       defaultValue = "false") boolean available,
                               @RequestParam Long restaurantId,
                               Model model) throws ProductException {

      ProductDto productDto = service.getProductById(id);


      productDto.setName(name);
      productDto.setDescription(description);
      productDto.setPrice(price);
      productDto.setImageUrl(imageUrl);
      productDto.setAvailable(available);
      productDto.setRestaurantDto(restaurantService.getById(restaurantId));

      service.updateProduct(productDto);

      model.addAttribute("product", productDto);

      return "redirect:/admin/products/{id}";
   }

   // DELETE
   @DeleteMapping("/{id}")
   public String deleteProduct(@PathVariable Long id) throws ProductException {
      service.deleteProduct(id);
      return "redirect:/admin/products";
   }
}
