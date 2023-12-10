package com.kcurryjib.mapper;

import com.kcurryjib.dto.*;
import com.kcurryjib.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mappers {

   @Autowired
   private ModelMapper modelMapper;

   public CartDto convertToCartDto(Cart cart) {
      /**
       * if you need to skip the field and not display it in JSON
       */
//      modelMapper.typeMap(Cart.class, CartDto.class)
//              .addMappings(mapper -> mapper.skip(CartDto::setCartProductsDto));

      CartDto cartDto = modelMapper.map(cart, CartDto.class);
      return cartDto;
   }

   public CartProductDto convertToCartProductDto(CartProduct cartProduct) {
      CartProductDto cartProductDto = modelMapper.map(cartProduct, CartProductDto.class);
      return cartProductDto;
   }

   public CustomerDto convertToCustomerDto(Customer customer) {
      CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
      return customerDto;
   }

   public EmployeeDto convertToEmployeeDto(Employee employee) {
      EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
      return employeeDto;
   }

   public OrderDto convertToOrderDto(Order order) {
      OrderDto orderDto = modelMapper.map(order, OrderDto.class);
      return orderDto;
   }

   public OrderProductDto convertToOrderProductDto(OrderProduct orderProduct) {
      OrderProductDto orderProductDto = modelMapper.map(orderProduct, OrderProductDto.class);
      return orderProductDto;
   }

   public ProductDto convertToProductDto(Product product) {
      ProductDto productDto = modelMapper.map(product, ProductDto.class);
      return productDto;
   }

   public RestaurantDto convertToRestaurantDto(Restaurant restaurant) {
      RestaurantDto restaurantDto = modelMapper.map(restaurant, RestaurantDto.class);
      return restaurantDto;
   }

   public ReviewDto convertToReviewDto(Review review) {
      ReviewDto reviewDto = modelMapper.map(review, ReviewDto.class);
      return reviewDto;
   }
}
