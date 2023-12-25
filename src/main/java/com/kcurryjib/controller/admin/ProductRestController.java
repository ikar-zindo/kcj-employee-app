package com.kcurryjib.controller.admin;

import com.kcurryjib.dto.ProductDto;
import com.kcurryjib.exceptions.ProductException;
import com.kcurryjib.service.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/products/rest")
public class ProductRestController {

   @Autowired
   private ProductService service;

   @GetMapping
   public ResponseEntity<List<ProductDto>> getProducts() {

      List<ProductDto> products = service.getAll();
      return new ResponseEntity<>(products, HttpStatus.OK);
   }

   @GetMapping("/{id}")
   public ResponseEntity<ProductDto> getProductId(@PathVariable Long id) {

      ProductDto product = service.getProductById(id);
      return new ResponseEntity<>(product, HttpStatus.OK);
   }

   @PostMapping
   public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) throws ProductException {

      ProductDto product = service.addProduct(productDto);
      return new ResponseEntity<>(product, HttpStatus.OK);
   }

   @PutMapping
   public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) throws ProductException {

      ProductDto product = service.addProduct(productDto);
      return new ResponseEntity<>(product, HttpStatus.OK);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity deleteProduct(@PathVariable Long id) throws ProductException {

      service.deleteProduct(id);
      return ResponseEntity.ok().build();
   }
}
