package com.kcurryjib.service.customer;

import com.kcurryjib.config.MapperUtil;
import com.kcurryjib.dto.CartDto;
import com.kcurryjib.dto.CartProductDto;
import com.kcurryjib.dto.CustomerDto;
import com.kcurryjib.dto.ProductDto;
import com.kcurryjib.entity.Cart;
import com.kcurryjib.entity.CartProduct;
import com.kcurryjib.entity.Customer;
import com.kcurryjib.entity.Product;
import com.kcurryjib.exception.list.CartException;
import com.kcurryjib.exception.list.CustomerException;
import com.kcurryjib.mapper.customer.CartMapper;
import com.kcurryjib.repo.CartProductRepository;
import com.kcurryjib.repo.CartRepository;
import com.kcurryjib.repo.CustomerRepository;
import com.kcurryjib.repo.ProductRepository;
import com.kcurryjib.service.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

   private final CartMapper cartMapper;

   private final CartRepository cartRepository;

   private final CartProductRepository cartProductRepository;

   private final ProductRepository productRepository;

   private final CustomerRepository customerRepository;

   private ProductService productService;

   @Autowired
   public CartService(CartMapper cartMapper,
                      CartRepository cartRepository,
                      CartProductRepository cartProductRepository,
                      ProductRepository productRepository,
                      CustomerRepository customerRepository,
                      ProductService productService) throws CartException {

      this.cartMapper = cartMapper;
      this.cartRepository = cartRepository;
      this.cartProductRepository = cartProductRepository;
      this.productRepository = productRepository;
      this.customerRepository = customerRepository;
      this.productService = productService;
   }


   public CustomerDto getCustomerById(Long customerId) throws CustomerException {
      CustomerDto customerDto = null;

      if (customerId != null) {
         Optional<Customer> customerOptional = customerRepository.findById(customerId);

         if (customerOptional.isPresent()) {
            customerDto = cartMapper.convertToCustomerDto(customerOptional.get());

         } else {
            throw new CustomerException(
                    String.format("Customer not found in database with id=%d",
                            customerId));
         }

      } else {
         throw new CustomerException("There is no customer ID to search for!");
      }

      return customerDto;
   }

   public CartProductDto addProductToCustomerCart(Long customerId, Long productId) {

      if (customerId != null && productId != null) {
         CustomerDto customerDto = getCustomerById(customerId);
         ProductDto productDto = productService.getProductById(productId);

         CartProductDto cartProductDto = new CartProductDto();

         cartProductDto.setCartDto(customerDto.getCartDto());
         cartProductDto.setProductDto(productDto);

         if (productDto != null && productDto.getId() != null) {
            Customer customer = customerRepository.findById(customerDto.getId()).orElse(null);
            Product product = productRepository.findById(productDto.getId()).orElse(null);

            if (customer != null && product != null) {
               CartProduct cartProduct = cartMapper.convertToCartProduct(cartProductDto);

               cartProduct.setCart(customer.getCart());
               cartProduct.setProduct(product);
               cartProduct.setCratedAt(LocalDateTime.now());

               CartProduct cartProductResponse = cartProductRepository.save(cartProduct);
               Long idResponse = cartProductResponse.getId();

               if (idResponse != null && idResponse > 0) {
                  return cartMapper.convertToCartProductDto(cartProductResponse);

               } else {
                  throw new CartException("");
               }
            } else {
               throw new CartException("");
            }
         } else {
            throw new CartException("");
         }

      } else {
         throw new CartException("");
      }
   }
}
