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

import java.util.Optional;

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

   // READ
   public EmployeeDto getEmployeeWithOrders(Long employeeId) throws EmployeeException {
      Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

      if (employeeOptional.isPresent()) {
         Employee employee = employeeOptional.get();

         return orderMapper.showEmployeeWithOrders(employee);

      } else {
         throw new EmployeeException("Employee not found with id: " + employeeId);
      }
   }

   // UPDATE - CREATED
   public OrderDto createdOrderStatus(Long id) throws OrderException {

      if (id != null) {
         Optional<Order> optionalOrder = orderRepository.findById(id);

         if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderStatus(OrderStatus.CREATED);

            Order orderResponse = orderRepository.save(order);

            if (orderResponse != null) {
               return orderMapper.convertToOrderDto(orderResponse);

            } else {
               throw new OrderException(
                       String.format("Failed to created order in database with Id=%d!",
                               id));
            }
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
   public OrderDto completedOrderStatus(Long id) throws OrderException {

      if (id != null) {
         Optional<Order> optionalOrder = orderRepository.findById(id);

         if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderStatus(OrderStatus.COMPLETED);

            Order orderResponse = orderRepository.save(order);

            if (orderResponse != null) {
               return orderMapper.convertToOrderDto(orderResponse);

            } else {
               throw new OrderException(
                       String.format("Failed to completed order in database with Id=%d!",
                               id));
            }
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
   public OrderDto cookingOrderStatus(Long id) throws OrderException {

      if (id != null) {
         Optional<Order> optionalOrder = orderRepository.findById(id);

         if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderStatus(OrderStatus.COOKING);

            Order orderResponse = orderRepository.save(order);

            if (orderResponse != null) {
               return orderMapper.convertToOrderDto(orderResponse);

            } else {
               throw new OrderException(
                       String.format("Failed to cooking order in database with Id=%d!",
                               id));
            }
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
   public OrderDto deliveringOrderStatus(Long id) throws OrderException {

      if (id != null) {
         Optional<Order> optionalOrder = orderRepository.findById(id);

         if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderStatus(OrderStatus.DELIVERING);

            Order orderResponse = orderRepository.save(order);

            if (orderResponse != null) {
               return orderMapper.convertToOrderDto(orderResponse);

            } else {
               throw new OrderException(
                       String.format("Failed to delivering order in database with Id=%d!",
                               id));
            }
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
   public OrderDto cancelledOrderStatus(Long id) throws OrderException {

      if (id != null) {
         Optional<Order> optionalOrder = orderRepository.findById(id);

         if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderStatus(OrderStatus.CANCELLED);

            Order orderResponse = orderRepository.save(order);

            if (orderResponse != null) {
               return orderMapper.convertToOrderDto(orderResponse);

            } else {
               throw new OrderException(
                       String.format("Failed to complete order in database with Id=%d!",
                               id));
            }
         } else {
            throw new OrderException(
                    String.format("Order not found in the database with Id=%d!",
                            id));
         }
      } else {
         throw new OrderException("The ID of the order to be cancelled is missing!");
      }
   }
}