package com.kcurryjib.controller.customer;

import com.kcurryjib.dto.CartProductDto;
import com.kcurryjib.dto.CustomerDto;
import com.kcurryjib.dto.ProductDto;
import com.kcurryjib.exception.list.CartException;
import com.kcurryjib.service.customer.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/customer")
public class CartController {

   @Autowired
   private CartService cartService;

   @GetMapping("/{customerId}")
   public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long customerId) {
      CustomerDto product = cartService.getCustomerById(customerId);
      return new ResponseEntity<>(product, HttpStatus.OK);
   }


   @PostMapping("/{customerId}/add/{productId}")
//   @PreAuthorize("hasRole('ROLE_CUSTOMER')")
   public ResponseEntity<CartProductDto> addProductToCustomerCart(
           @PathVariable Long customerId,
           @PathVariable Long productId) throws CartException {

      CartProductDto cartProductDto = cartService.addProductToCustomerCart(customerId, productId);
      return new ResponseEntity<>(cartProductDto, HttpStatus.OK);
   }
}
