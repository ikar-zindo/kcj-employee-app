package com.kcurryjib.service.employee;

import com.kcurryjib.config.MapperUtil;
import com.kcurryjib.dto.EmployeeDto;
import com.kcurryjib.dto.OrderDto;
import com.kcurryjib.entity.Employee;
import com.kcurryjib.entity.Order;
import com.kcurryjib.exception.list.EmployeeException;
import com.kcurryjib.mapper.employee.EmployeeMapper;
import com.kcurryjib.mapper.employee.OrderMapper;
import com.kcurryjib.repo.EmployeeRepository;
import com.kcurryjib.repo.OrderProductRepository;
import com.kcurryjib.repo.OrderRepository;
import com.kcurryjib.repo.ProductRepository;
import com.kcurryjib.service.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

   private final EmployeeMapper employeeMapper;

   private final OrderMapper orderMapper;

   private final OrderRepository orderRepository;

   private final OrderProductRepository orderProductRepository;

   private final ProductRepository productRepository;

   private final EmployeeRepository employeeRepository;

   private final ProductService productService;

   @Autowired
   public OrderService(EmployeeMapper employeeMapper,
                       OrderMapper orderMapper,
                       OrderRepository orderRepository,
                       OrderProductRepository orderProductRepository,
                       ProductRepository productRepository,
                       EmployeeRepository employeeRepository,
                       ProductService productService) {

      this.employeeMapper = employeeMapper;
      this.orderMapper = orderMapper;
      this.orderRepository = orderRepository;
      this.orderProductRepository = orderProductRepository;
      this.productRepository = productRepository;
      this.employeeRepository = employeeRepository;
      this.productService = productService;
   }

   // READ
   public List<EmployeeDto> getAllActiveEmployee() throws EmployeeException {
      List<Employee> employees = new ArrayList<>(employeeRepository.findAll());

      return MapperUtil.convertlist(employees, employeeMapper::convertToEmployeeDto);
   }

   // READ
   public List<OrderDto> getAllOrders() throws EmployeeException {
      List<Order> orders = new ArrayList<>(orderRepository.findAll());

      return MapperUtil.convertlist(orders, orderMapper::convertToOrderDto);
   }
}

