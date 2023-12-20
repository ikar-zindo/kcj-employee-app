package com.kcurryjib.controller.admin;

import com.kcurryjib.dto.ProductDto;
import com.kcurryjib.dto.RestaurantDto;
import com.kcurryjib.exceptions.ProductException;
import com.kcurryjib.service.admin.ProductService;
import com.kcurryjib.service.admin.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

   @GetMapping
   public String getAllProducts(Model model) {
      List<ProductDto> productsDto = service.getAll();

      model.addAttribute("products", productsDto);

      return "/admin/products/all";
   }

   @GetMapping("/{id}")
   public String getProductId(@PathVariable Long id, Model model) {
      ProductDto product = service.getProductById(id);
      model.addAttribute("product", product);
      return "/admin/products/info";
   }

   @GetMapping("/add")
   public String addProductForm(Model model) throws ProductException {
      Iterable<RestaurantDto> restaurantsDto = restaurantService.getAll();

      model.addAttribute("restaurants", restaurantsDto);

      return "/admin/products/add";
   }

   @PostMapping("/add")
   public String addProduct(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam BigDecimal price,
                            @RequestParam String imageUrl,
                            @RequestParam(name = "available", required = false, defaultValue = "false") boolean available,
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

   @PutMapping
   public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) throws ProductException {
      ProductDto product = service.addProduct(productDto);
      return new ResponseEntity<>(product, HttpStatus.OK);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity deleteProduct(@PathVariable Long id) {
      service.delete(id);
      return ResponseEntity.ok().build();
   }
}
