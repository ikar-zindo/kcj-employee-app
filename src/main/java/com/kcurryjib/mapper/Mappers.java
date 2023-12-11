package com.kcurryjib.mapper;

import com.kcurryjib.dto.*;
import com.kcurryjib.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mappers {

   @Autowired
   private ModelMapper modelMapper;

   /**
    * converts entity to DTO
    */

   // convert to CartDo
   public CartDto convertToCartDto(Cart cart) {
      CartDto cartDto = modelMapper.map(cart, CartDto.class);

      cartDto.setCustomerDto(convertToCustomerDto(cart.getCustomer()));
//      cartDto.setCartProductsDto(convertToCartProductsDto(cart.getCartProducts())); // need List

      return cartDto;
   }

//   public List<CartProductDto> convertToCartProductsDto(List<CartProduct> cartProducts) {
//      return cartProducts.stream()
//              .map(this::convertToCartProductDto)
//              .collect(Collectors.toList());
//   }

   // convert to CartProductDto
   public CartProductDto convertToCartProductDto(CartProduct cartProduct) {
      CartProductDto cartProductDto = modelMapper.map(cartProduct, CartProductDto.class);

      cartProductDto.setCartDto(convertToCartDto(cartProduct.getCart()));
      cartProductDto.setProductDto(convertToProductDto(cartProduct.getProduct()));

      return cartProductDto;
   }

   // convert to CustomerDto
   public CustomerDto convertToCustomerDto(Customer customer) {
      CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);

      customerDto.setCartDto(convertToCartDto(customer.getCart()));
//      customerDto.setOrdersDto(convertToOrdersDto(customer.getOrders())); // need List
//      customerDto.setReviewsDto(convertToReviewsDto(customer.getReviews())); // need List

      return customerDto;
   }

//   public List<OrderDto> convertToOrdersDto(List<Order> orders) {
//      return orders.stream()
//              .map(this::convertToOrderDto)
//              .collect(Collectors.toList());
//   }

//   public List<ReviewDto> convertToReviewsDto(List<Review> reviews) {
//      return reviews.stream()
//              .map(this::convertToReviewDto)
//              .collect(Collectors.toList());
//   }

   // convert to EmployeeDto
   public EmployeeDto convertToEmployeeDto(Employee employee) {
      EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

      employeeDto.setRestaurantDto(convertToRestaurantDto(employee.getRestaurant()));
//      employeeDto.setOrdersDto(convertToOrdersDto(employee.getOrders())); // need List

      return employeeDto;
   }

   // convert to OrderDto
   public OrderDto convertToOrderDto(Order order) {
      OrderDto orderDto = modelMapper.map(order, OrderDto.class);

      orderDto.setCustomerDto(convertToCustomerDto(order.getCustomer()));
      orderDto.setRestaurantDto(convertToRestaurantDto(order.getRestaurant()));
      orderDto.setEmployeeDto(convertToEmployeeDto(order.getEmployee()));
//      orderDto.setOrderProductsDto(convertToOrderProductsDto(order.getOrderProducts())); // need List

      return orderDto;
   }

//   public List<OrderProductDto> convertToOrderProductsDto(List<OrderProduct> orderProducts) {
//      return orderProducts.stream()
//              .map(this::convertToOrderProductDto)
//              .collect(Collectors.toList());
//   }

   // convert to OrderProductDto
   public OrderProductDto convertToOrderProductDto(OrderProduct orderProduct) {
      OrderProductDto orderProductDto = modelMapper.map(orderProduct, OrderProductDto.class);

      orderProductDto.setOrderDto(convertToOrderDto(orderProduct.getOrder()));
      orderProductDto.setProductDto(convertToProductDto(orderProduct.getProduct()));

      return orderProductDto;
   }

   public ProductDto convertToProductDto(Product product) {
      ProductDto productDto = modelMapper.map(product, ProductDto.class);

      productDto.setRestaurantDto(convertToRestaurantDto(product.getRestaurant()));
//      productDto.setCartProductsDto(convertToCartProductsDto(product.getCartProducts())); // need List
//      productDto.setOrderProductsDto(convertToOrderProductsDto(product.getOrderProducts())); // need List

      return productDto;
   }

   // convert to RestaurantDto
   public RestaurantDto convertToRestaurantDto(Restaurant restaurant) {
      RestaurantDto restaurantDto = modelMapper.map(restaurant, RestaurantDto.class);

//      restaurantDto.setProductsDto(convertToProductsDto(restaurant.getProducts()));
//      restaurantDto.setReviewsDto(convertToReviewsDto(restaurant.getReviews()));
//      restaurantDto.setOrdersDto(convertToOrdersDto(restaurant.getOrders()));
//      restaurantDto.setEmployeesDto(convertToEmployeesDto(restaurant.getEmployees()));

      return restaurantDto;
   }

//   public List<ProductDto> convertToProductsDto(List<Product> products) {
//      return products.stream()
//              .map(this::convertToProductDto)
//              .collect(Collectors.toList());
//   }

//   public List<EmployeeDto> convertToEmployeesDto(List<Employee> employees) {
//      return employees.stream()
//              .map(this::convertToEmployeeDto)
//              .collect(Collectors.toList());
//   }

   // convert to ReviewDto
   public ReviewDto convertToReviewDto(Review review) {
      ReviewDto reviewDto = modelMapper.map(review, ReviewDto.class);

//      reviewDto.setRestaurantDto(convertToRestaurantDto(review.getRestaurant()));
//      reviewDto.setCustomerDto(convertToCustomerDto(review.getCustomer()));

      return reviewDto;
   }

   /**
    * converts DTO to entity
    */
}
