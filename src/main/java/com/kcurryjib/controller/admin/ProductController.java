package com.kcurryjib.controller.admin;

import com.kcurryjib.dto.ProductDto;
import com.kcurryjib.exceptions.ProductException;
import com.kcurryjib.service.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

   @Autowired
   private ProductService service;

   @GetMapping
   public String getAllProducts(Model model) {
      List<ProductDto> productsDto = service.getAll();

      model.addAttribute("products", productsDto);

      return "admin/products/all";
   }

   @GetMapping("/{id}")
   public String getProductId(@PathVariable Long id, Model model) {
      ProductDto product = service.getProductById(id);
      model.addAttribute("product", product);
      return "admin/products/info";
   }

   @GetMapping("/add")
   public String addProductForm(Model model) throws ProductException {
      model.addAttribute("productDto", new ProductDto());
      return "admin/products/add";
   }

   @PostMapping
   public String addProduct(@RequestBody ProductDto productDto, Model model) throws ProductException {
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
