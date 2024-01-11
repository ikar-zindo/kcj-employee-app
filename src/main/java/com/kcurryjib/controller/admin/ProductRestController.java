package com.kcurryjib.controller.admin;

import com.kcurryjib.dto.ProductDto;
import com.kcurryjib.exception.list.ProductException;
import com.kcurryjib.service.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/products")
public class ProductRestController {

   @Autowired
   private ProductService service;

   @GetMapping
   public ResponseEntity<List<ProductDto>> getProducts() {

      List<ProductDto> products = service.getAll();
      return new ResponseEntity<>(products, HttpStatus.OK);
   }

   @GetMapping("/{id}")
   @PreAuthorize("hasRole('ROLE_ADMIN')")
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
