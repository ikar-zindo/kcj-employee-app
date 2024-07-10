package com.kcj_employee_app.mapper.admin;

import com.kcj_employee_app.dto.CustomerDto;
import com.kcj_employee_app.dto.ReviewDto;
import com.kcj_employee_app.entity.Customer;
import com.kcj_employee_app.entity.Review;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

   @Autowired
   private ModelMapper mapper;

   // convert to DTO
   public ReviewDto convertToReviewDto(Review review) {
      return mapper.map(review, ReviewDto.class);
   }

   public List<ReviewDto> convertToReviewsDto(List<Review> reviews) {
      return reviews.stream()
              .map(this::convertToReviewDto)
              .collect(Collectors.toList());
   }

   public CustomerDto convertToCustomerDto(Customer customer) {
      return mapper.map(customer, CustomerDto.class);
   }

   public CustomerDto shortCustomerDto(Customer customer) {

//      "id"
//      "firstName"
//      "lastName"
      mapper.typeMap(Customer.class, CustomerDto.class)
              .addMappings(m -> m.skip(CustomerDto::setEmail))
              .addMappings(m -> m.skip(CustomerDto::setPassword))
              .addMappings(m -> m.skip(CustomerDto::setPhoneNumber))
              .addMappings(m -> m.skip(CustomerDto::setAddress))
              .addMappings(m -> m.skip(CustomerDto::setPostalCode))
              .addMappings(m -> m.skip(CustomerDto::setRole))
              .addMappings(m -> m.skip(CustomerDto::setCreatedAt))
              .addMappings(m -> m.skip(CustomerDto::setBlocked))
              .addMappings(m -> m.skip(CustomerDto::setCartDto))
              .addMappings(m -> m.skip(CustomerDto::setOrdersDto))
              .addMappings(m -> m.skip(CustomerDto::setReviewsDto));

      CustomerDto customerDto = mapper.map(customer, CustomerDto.class);
      customerDto.setReviewsDto(convertToReviewsDto(customer.getReviews()));

      return customerDto;
   }

   public CustomerDto customerInfoDelivery(Customer customer) {

//      "id"
//      "firstName"
//      "lastName"
//      "email"
//      "phoneNumber"
//      "address"
//      "postalCode"
      mapper.typeMap(Customer.class, CustomerDto.class)
              .addMappings(m -> m.skip(CustomerDto::setPassword))
              .addMappings(m -> m.skip(CustomerDto::setCreatedAt))
              .addMappings(m -> m.skip(CustomerDto::setBlocked))
              .addMappings(m -> m.skip(CustomerDto::setCartDto))
              .addMappings(m -> m.skip(CustomerDto::setOrdersDto))
              .addMappings(m -> m.skip(CustomerDto::setReviewsDto));

      CustomerDto customerDto = mapper.map(customer, CustomerDto.class);

      return customerDto;
   }

   public List<CustomerDto> convertToCustomersDto(List<Customer> customers) {
      return customers.stream()
              .map(this::convertToCustomerDto)
              .collect(Collectors.toList());
   }

   // convert to entity
   public Customer convertToCustomer(CustomerDto customerDto) {
      Customer customer = mapper.map(customerDto, Customer.class);

      return customer;
   }

   public List<Customer> convertToCustomers(List<CustomerDto> customersDto) {
      return customersDto.stream()
              .map(this::convertToCustomer)
              .collect(Collectors.toList());
   }
}
