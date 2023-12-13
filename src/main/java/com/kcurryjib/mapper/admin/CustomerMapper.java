package com.kcurryjib.mapper.admin;

import com.kcurryjib.dto.CustomerDto;
import com.kcurryjib.entity.Customer;
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
   public CustomerDto convertToCustomerDto(Customer customer) {
      CustomerDto customerDto = mapper.map(customer, CustomerDto.class);

//      customerDto.setReviewsDto(reviewMapper.convertToReviewsDto(customer.getReviews()));
//      customerDto.setOrdersDto(orderMapper.convertToOrdersDto(customer.getOrders()));

      return customerDto;
   }

   public CustomerDto shortCustomerDto(Customer customer) {


//      "id"
//      "firstName"
//      "lastName"
      mapper.createTypeMap(Customer.class, CustomerDto.class)
              .addMappings(m -> m.skip(CustomerDto::setEmail))
              .addMappings(m -> m.skip(CustomerDto::setPassword))
              .addMappings(m -> m.skip(CustomerDto::setPhoneNumber))
              .addMappings(m -> m.skip(CustomerDto::setAddress))
              .addMappings(m -> m.skip(CustomerDto::setPostalCode))
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
