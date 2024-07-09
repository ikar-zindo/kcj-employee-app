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
import com.kcurryjib.service.admin.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

   private final EmployeeRepository employeeRepository;

   private final OrderRepository orderRepository;

   private final OrderMapper orderMapper;

   private final EmployeeService employeeService;

   @Autowired
   public OrderService(EmployeeRepository employeeRepository,
                       OrderMapper orderMapper,
                       OrderRepository orderRepository,
                       EmployeeService employeeService) throws EmployeeException {

      this.employeeRepository = employeeRepository;
      this.orderMapper = orderMapper;
      this.orderRepository = orderRepository;
      this.employeeService = employeeService;
   }

   // READ - GET TODAY ALL ORDERS
   public List<OrderDto> getTodayNewOrders() {
      List<Order> orders = new ArrayList<>(orderRepository.findAll()).stream()
              .filter(order -> order.getCreatedAt().toLocalDate().isEqual(getToday()) &&
                      order.getOrderStatus().equals(OrderStatus.CREATED))
              .sorted(Comparator.comparing(Order::getCreatedAt))
              .collect(Collectors.toList());

//      return MapperUtil.convertlist(orders, orderMapper::convertToOrderDto);
      return orderMapper.convertToOrdersDto(orders);
   }

   // READ - ALL EMPLOYEE ORDERS
   public EmployeeDto getEmployeeWithOrders(UUID employeeId) throws EmployeeException {

      if (employeeId != null) {
         Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

         if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();

            return orderMapper.showEmployeeWithOrders(employee);

         } else {
            throw new EmployeeException("Employee not found with id=: " + employeeId);
         }
      } else {
         throw new EmployeeException("Employee not passed to method");
      }
   }

   // READ - GET TODAY ORDERS CURRENT EMPLOYEE
   public List<OrderDto> getEmployeeTodayOrders(UUID employeeId) {

      if (employeeId != null) {
         List<OrderDto> ordersDto = employeeService.getEmployeeById(employeeId).getOrdersDto();

         return ordersDto.stream()
                 .filter(order -> order.getCreatedAt().toLocalDate().isEqual(getToday()))
                 .sorted(Comparator.comparing(OrderDto::getUpdateAt))
                 .collect(Collectors.toList());
      } else {
         throw new EmployeeException("Employee not passed to method");
      }

   }

   // READ - GET COMPLETED TODAY ORDERS CURRENT EMPLOYEE
   public List<OrderDto> getEmployeeCompletedOrders(UUID employeeId) {

      if (employeeId != null) {
         List<OrderDto> ordersDto = employeeService.getEmployeeById(employeeId).getOrdersDto();

         return ordersDto.stream()
                 .filter(order ->
                         order.getOrderStatus().equals(OrderStatus.COMPLETED) ||
                                 order.getOrderStatus().equals(OrderStatus.CANCELLED))
                 .sorted(Comparator.comparing(OrderDto::getUpdateAt).reversed())
                 .collect(Collectors.toList());
      } else {
         throw new EmployeeException("Employee not passed to method");
      }
   }

   // READ - GET PROCESSING TODAY ORDERS CURRENT EMPLOYEE
   public List<OrderDto> getEmployeeProgressingTodayOrders(UUID employeeId) {

      if (employeeId != null) {
         List<OrderDto> ordersDto = employeeService.getEmployeeById(employeeId).getOrdersDto();

         return ordersDto.stream()
                 .filter(order ->
                         order.getOrderStatus().equals(OrderStatus.COOKING) ||
                                 order.getOrderStatus().equals(OrderStatus.DELIVERING))
                 .sorted(Comparator.comparing(OrderDto::getUpdateAt))
                 .collect(Collectors.toList());
      } else {
         throw new EmployeeException("Employee not passed to method");
      }
   }

   // UPDATE ORDER - CREATED
   public void createdOrderStatus(UUID orderId) throws OrderException {

      if (orderId != null) {
         Optional<Order> optionalOrder = orderRepository.findById(orderId);

         if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderStatus(OrderStatus.CREATED);
            order.setUpdateAt(LocalDateTime.now());

            orderRepository.save(order);

         } else {
            throw new OrderException(
                    String.format("Order not found in the database with Id=%s!",
                            orderId));
         }
      } else {
         throw new OrderException("The ID of the order to be created is missing!");
      }
   }

   // UPDATE ORDER - COMPLETED
   public void completedOrderStatus(UUID orderId) throws OrderException {

      if (orderId != null) {
         Optional<Order> optionalOrder = orderRepository.findById(orderId);

         if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderStatus(OrderStatus.COMPLETED);
            order.setUpdateAt(LocalDateTime.now());

            orderRepository.save(order);

         } else {
            throw new OrderException(
                    String.format("Order not found in the database with Id=%s!",
                            orderId));
         }
      } else {
         throw new OrderException("The ID of the order to be completed is missing!");
      }
   }

   // UPDATE ORDER - COOKING
   public void cookingOrderStatus(UUID employeeId, UUID orderId) throws OrderException {

      if (employeeId != null) {
         Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

         if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();

            if (orderId != null) {
               Optional<Order> optionalOrder = orderRepository.findById(orderId);

               if (optionalOrder.isPresent()) {
                  Order order = optionalOrder.get();
                  order.setOrderStatus(OrderStatus.COOKING);
                  order.setUpdateAt(LocalDateTime.now());
                  order.setEmployee(employee);

                  orderRepository.save(order);

               } else {
                  throw new OrderException(
                          String.format("Order not found in the database with Id=%s!",
                                  orderId));
               }
            } else {
               throw new OrderException("The ID of the order to be cooking is missing!");
            }
         } else {
            throw new EmployeeException("Employee was not found!!");
         }
      } else {
         throw new EmployeeException("Employee not passed to method");
      }
   }

   // UPDATE ORDER - DELIVERING
   public void deliveringOrderStatus(UUID orderId) throws OrderException {

      if (orderId != null) {
         Optional<Order> optionalOrder = orderRepository.findById(orderId);

         if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderStatus(OrderStatus.DELIVERING);
            order.setUpdateAt(LocalDateTime.now());

            orderRepository.save(order);

         } else {
            throw new OrderException(
                    String.format("Order not found in the database with Id=%s!",
                            orderId));
         }
      } else {
         throw new OrderException("The ID of the order to be delivering is missing!");
      }
   }

   // UPDATE ORDER - CANCELLED
   public void cancelledOrderStatus(UUID orderId) throws OrderException {

      if (orderId != null) {
         Optional<Order> optionalOrder = orderRepository.findById(orderId);

         if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderStatus(OrderStatus.CANCELLED);
            order.setUpdateAt(LocalDateTime.now());

            orderRepository.save(order);

         } else {
            throw new OrderException(
                    String.format("Order not found in the database with Id=%s!",
                            orderId));
         }
      } else {
         throw new OrderException("The ID of the order to be cancelled is missing!");
      }
   }

   public LocalDate getToday() {
      return LocalDate.now();
   }
}