//package com.kcurryjib.mapper;
//
//import com.kcurryjib.dto.*;
//import com.kcurryjib.entity.*;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//public class Mappers {
//
//   @Autowired
//   private ModelMapper modelMapper;
//
//   /**
//    * converts entity to DTO
//    */
//
//   public CartDto convertToCartDto(Cart cart) {
//       // if you need to skip the field and not display it in JSON
////      modelMapper.typeMap(Cart.class, CartDto.class)
////              .addMappings(mapper -> mapper.skip(CartDto::setCartProductsDto))
//
//      CartDto cartDto = modelMapper.map(cart, CartDto.class);
//
//      cartDto.setCustomerDto(convertToCustomerDto(cart.getCustomer()));
//      cartDto.setCartProductsDto(convertToCartProductsDto(cart.getCartProducts())); // need List
//
//      return cartDto;
//   }
//
//   public List<CartProductDto> convertToCartProductsDto(List<CartProduct> cartProducts) {
//      return cartProducts.stream()
//              .map(this::convertToCartProductDto)
//              .collect(Collectors.toList());
//   }
//
//   public CartProductDto convertToCartProductDto(CartProduct cartProduct) {
//      CartProductDto cartProductDto = modelMapper.map(cartProduct, CartProductDto.class);
//
//      cartProductDto.setCartDto(convertToCartDto(cartProduct.getCart()));
//      cartProductDto.setProductDto(convertToProductDto(cartProduct.getProduct()));
//      cartProductDto.setCustomerDto(convertToCustomerDto(cartProduct.getCustomer()));
//
//      return cartProductDto;
//   }
//
//   public CustomerDto convertToCustomerDto(Customer customer) {
//      CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
//
//      customerDto.setCartDto(convertToCartDto(customer.getCart()));
//      customerDto.setCartProductsDto(convertToCartProductsDto(customer.getCartProducts())); // need List
//      customerDto.setOrderProductsDto(convertToOrderProductsDto(customer.getOrderProducts())); // need List
//
//      return customerDto;
//   }
//
//   public EmployeeDto convertToEmployeeDto(Employee employee) {
//      EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
//
//      employeeDto.setRestaurantDto(convertToRestaurantDto(employee.getRestaurant()));
//
//      return employeeDto;
//   }
//
//   public OrderDto convertToOrderDto(Order order) {
//      OrderDto orderDto = modelMapper.map(order, OrderDto.class);
//
//      orderDto.setCustomerDto(convertToCustomerDto(order.getCustomer()));
//      orderDto.setRestaurantDto(convertToRestaurantDto(order.getRestaurant()));
//      orderDto.setEmployeeDto(convertToEmployeeDto(order.getEmployee()));
//      orderDto.setOrderProductsDto(convertToOrderProductsDto(order.getOrderProducts())); // need List
//
//      return orderDto;
//   }
//
//   public List<OrderProductDto> convertToOrderProductsDto(List<OrderProduct> orderProducts) {
//      return orderProducts.stream()
//              .map(this::convertToOrderProductDto)
//              .collect(Collectors.toList());
//   }
//
//   public OrderProductDto convertToOrderProductDto(OrderProduct orderProduct) {
//      OrderProductDto orderProductDto = modelMapper.map(orderProduct, OrderProductDto.class);
//
//      orderProductDto.setOrderDto(convertToOrderDto(orderProduct.getOrder()));
//      orderProductDto.setProductDto(convertToProductDto(orderProduct.getProduct()));
//      orderProductDto.setCustomerDto(convertToCustomerDto(orderProduct.getCustomer()));
//
//      return orderProductDto;
//   }
//
//   public ProductDto convertToProductDto(Product product) {
//      ProductDto productDto = modelMapper.map(product, ProductDto.class);
//
//      productDto.setCartProductsDto(convertToCartProductsDto(product.getCartProducts())); // need List
//      productDto.setRestaurantDto(convertToRestaurantDto(product.getRestaurant()));
//
//      return productDto;
//   }
//
//   public RestaurantDto convertToRestaurantDto(Restaurant restaurant) {
//      return modelMapper.map(restaurant, RestaurantDto.class);
//   }
//
//   public ReviewDto convertToReviewDto(Review review) {
//      ReviewDto reviewDto = modelMapper.map(review, ReviewDto.class);
//
//      reviewDto.setRestaurantDto(convertToRestaurantDto(review.getRestaurant()));
//      reviewDto.setCustomerDto(convertToCustomerDto(review.getCustomer()));
//
//      return reviewDto;
//   }
//
//   /**
//    * converts DTO to entity
//    */
//}
