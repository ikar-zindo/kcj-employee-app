package com.kcurryjib.controller.admin;

import com.kcurryjib.dto.ProductDto;
import com.kcurryjib.service.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/product")
public class ProductController {

   @Autowired
   private ProductService productService;

   @GetMapping
   public ResponseEntity<List<ProductDto>> getAll() {
      List<ProductDto> productsDto = productService.gatAll();

      return new ResponseEntity<>(productsDto, HttpStatus.OK);
   }
}