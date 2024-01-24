package com.kcurryjib.service.employee;

import com.kcurryjib.dto.EmployeeDto;
import com.kcurryjib.dto.OrderDto;
import com.kcurryjib.entity.Employee;
import com.kcurryjib.entity.Order;
import com.kcurryjib.entity.enums.OrderStatus;
import com.kcurryjib.exception.list.EmployeeException;
import com.kcurryjib.exception.list.OrderException;
import com.kcurryjib.mapper.employee.OrderMapper;
import com.kcurryjib.repo.EmployeeRepository;
import com.kcurryjib.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

   private final EmployeeRepository employeeRepository;

   private final OrderRepository orderRepository;

   private final OrderMapper orderMapper;

   @Autowired
   public OrderService(EmployeeRepository employeeRepository,
                       OrderMapper orderMapper,
                       OrderRepository orderRepository) throws EmployeeException {

      this.employeeRepository = employeeRepository;
      this.orderMapper = orderMapper;
      this.orderRepository = orderRepository;
   }

   // READ - ALL EMPLOYEE ORDERS
   public EmployeeDto getEmployeeWithOrders(Long employeeId) throws EmployeeException {
      Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

      if (employeeOptional.isPresent()) {
         Employee employee = employeeOptional.get();

         return orderMapper.showEmployeeWithOrders(employee);

      } else {
         throw new EmployeeException("Employee not found with id: " + employeeId);
      }
   }

   // READ - GET TODAY EMPLOYEE ORDERS
   public List<OrderDto> getTodayOrders(Long employeeId) {
      List<OrderDto> ordersDto = getEmployeeWithOrders(employeeId).getOrdersDto();

      return ordersDto.stream()
              .filter(order -> order.getCreatedAt().toLocalDate().isEqual(getToday()))
              .collect(Collectors.toList());
   }

   // UPDATE - CREATED
   public void createdOrderStatus(Long id) throws OrderException {

      if (id != null) {
         Optional<Order> optionalOrder = orderRepository.findById(id);

         if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderStatus(OrderStatus.CREATED);
            order.setUpdateAt(LocalDateTime.now());

            orderRepository.save(order);

         } else {
            throw new OrderException(
                    String.format("Order not found in the database with Id=%d!",
                            id));
         }
      } else {
         throw new OrderException("The ID of the order to be created is missing!");
      }
   }

   // UPDATE - COMPLETED
   public void completedOrderStatus(Long id) throws OrderException {

      if (id != null) {
         Optional<Order> optionalOrder = orderRepository.findById(id);

         if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderStatus(OrderStatus.COMPLETED);
            order.setUpdateAt(LocalDateTime.now());

            orderRepository.save(order);

         } else {
            throw new OrderException(
                    String.format("Order not found in the database with Id=%d!",
                            id));
         }
      } else {
         throw new OrderException("The ID of the order to be completed is missing!");
      }
   }

   // UPDATE - COOKING
   public void cookingOrderStatus(Long id) throws OrderException {

      if (id != null) {
         Optional<Order> optionalOrder = orderRepository.findById(id);

         if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderStatus(OrderStatus.COOKING);
            order.setUpdateAt(LocalDateTime.now());

            orderRepository.save(order);

         } else {
            throw new OrderException(
                    String.format("Order not found in the database with Id=%d!",
                            id));
         }
      } else {
         throw new OrderException("The ID of the order to be cooking is missing!");
      }
   }

   // UPDATE - DELIVERING
   public void deliveringOrderStatus(Long id) throws OrderException {

      if (id != null) {
         Optional<Order> optionalOrder = orderRepository.findById(id);

         if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderStatus(OrderStatus.DELIVERING);
            order.setUpdateAt(LocalDateTime.now());

            orderRepository.save(order);

         } else {
            throw new OrderException(
                    String.format("Order not found in the database with Id=%d!",
                            id));
         }
      } else {
         throw new OrderException("The ID of the order to be delivering is missing!");
      }
   }

   // UPDATE - CANCELLED
   public void cancelledOrderStatus(Long id) throws OrderException {

      if (id != null) {
         Optional<Order> optionalOrder = orderRepository.findById(id);

         if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderStatus(OrderStatus.CANCELLED);
            order.setUpdateAt(LocalDateTime.now());

            orderRepository.save(order);

         } else {
            throw new OrderException(
                    String.format("Order not found in the database with Id=%d!",
                            id));
         }
      } else {
         throw new OrderException("The ID of the order to be cancelled is missing!");
      }
   }

   public LocalDate getToday() {
      return LocalDate.now();
   }
}