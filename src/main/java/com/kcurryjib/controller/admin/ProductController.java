package com.kcurryjib.controller.admin;

import com.kcurryjib.dto.ProductDto;
import com.kcurryjib.dto.RestaurantDto;
import com.kcurryjib.exceptions.ProductException;
import com.kcurryjib.service.admin.ProductService;
import com.kcurryjib.service.admin.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/admin/products")
@SessionAttributes("editProducts")
public class ProductController {

   private final ProductService service;

   private final RestaurantService restaurantService;

   @Autowired
   public ProductController(ProductService service, RestaurantService restaurantService) {
      this.service = service;
      this.restaurantService = restaurantService;
   }

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


   /**
    * old version
    */


   // CREATE
   @GetMapping(value = "add")
   public String addProduct(@ModelAttribute("product") ProductDto productDto, Model model) {

      productDto.setImageUrl("/images/products/1.jpg");
      productDto.setName("new product");
      model.addAttribute("restaurants", restaurantService.getAll());

      return "admin/products/add";
   }

   // CREATE
   @PostMapping(value = "add")
   public String createProduct(@ModelAttribute("product") @Valid ProductDto productDto,
                               BindingResult result,
                               @RequestParam(name = "restaurantId") Long restaurantId,
                               Model model) throws ProductException {

      RestaurantDto restaurantDto = restaurantService.getById(restaurantId);
      productDto.setRestaurantDto(restaurantDto);

      if (result.hasErrors()) {
         model.addAttribute("product", productDto);
         model.addAttribute("restaurants", restaurantService.getAll());
         return "admin/products/add";
      }

      service.addProduct(productDto);
      return "redirect:/admin/products";
   }

   /**
    * old version
    */
//    CREATE
//   @PostMapping("/add")
//   public String addProduct(@RequestParam @Valid String name,
//                            @RequestParam @Valid String description,
//                            @RequestParam @Valid BigDecimal price,
//                            @RequestParam String imageUrl,
//                            @RequestParam(name = "available", required = false,
//                                    defaultValue = "false") boolean available,
//                            @RequestParam Long restaurantId,
//                            Model model) throws ProductException {
//
//      ProductDto productDto = new ProductDto();
//
//      productDto.setName(name);
//      productDto.setDescription(description);
//      productDto.setPrice(price);
//      productDto.setImageUrl(imageUrl);
//      productDto.setAvailable(available);
//      productDto.setRestaurantDto(restaurantService.getById(restaurantId));
//
//      service.addProduct(productDto);
//
//      return "redirect:/admin/products";
//   }

   // UPDATE
//   @GetMapping("/{id}/edit")
//   public String editProduct(@PathVariable(value = "id") Long id, Model model) throws ProductException {
//      if (service.getProductById(id) == null) {
//         return "redirect:/admin/products";
//      }
//
//      ProductDto productDto = service.getProductById(id);
//      Iterable<RestaurantDto> restaurantsDto = restaurantService.getAll();
//
//      model.addAttribute("product", productDto);
//      model.addAttribute("restaurants", restaurantsDto);
//
//      return "/admin/products/edit";
//   }
//
//   // UPDATE
//   @PostMapping("/{id}/edit")
//   public String updateProduct(@PathVariable(value = "id") Long id,
//                               @RequestParam String name,
//                               @RequestParam String description,
//                               @RequestParam BigDecimal price,
//                               @RequestParam String imageUrl,
//                               @RequestParam(name = "available", required = false,
//                                       defaultValue = "false") boolean available,
//                               @RequestParam Long restaurantId,
//                               Model model) throws ProductException {
//
//      ProductDto productDto = service.getProductById(id);
//
//      productDto.setName(name);
//      productDto.setDescription(description);
//      productDto.setPrice(price);
//      productDto.setImageUrl(imageUrl);
//      productDto.setAvailable(available);
//      productDto.setRestaurantDto(restaurantService.getById(restaurantId));
//
//      service.updateProduct(productDto);
//
//      model.addAttribute("product", productDto);
//
//      return "redirect:/admin/products/{id}";
//   }

   // DELETE
   @DeleteMapping("/{id}")
   public String deleteProduct(@PathVariable Long id) throws ProductException {
      service.deleteProduct(id);
      return "redirect:/admin/products";
   }
}
