package com.kcurryjib.service;

import com.kcurryjib.config.MapperUtil;
import com.kcurryjib.dto.CustomerDto;
import com.kcurryjib.entity.Customer;
import com.kcurryjib.mapper.CustomerMapper;
import com.kcurryjib.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

   @Autowired
   private CustomerRepository customerRepository;

   @Autowired
   private CustomerMapper customerMapper;

   public List<CustomerDto> getAll() {
      List<Customer> customers = new ArrayList<>(customerRepository.findAll());

      return MapperUtil.convertlist(customers, customerMapper::convertToCustomerDto);
   }
}
