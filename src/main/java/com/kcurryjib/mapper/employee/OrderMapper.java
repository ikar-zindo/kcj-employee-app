package com.kcurryjib.mapper.employee;

import com.kcurryjib.dto.EmployeeDto;
import com.kcurryjib.dto.OrderDto;
import com.kcurryjib.dto.OrderProductDto;
import com.kcurryjib.dto.ProductDto;
import com.kcurryjib.entity.Employee;
import com.kcurryjib.entity.Order;
import com.kcurryjib.entity.OrderProduct;
import com.kcurryjib.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

   @Autowired
   private ModelMapper mapper;

   // convert to DTO
   public ProductDto convertToProductDto(Product product) {
      return mapper.map(product, ProductDto.class);
   }

   public OrderProductDto convertToOrderProductDto(OrderProduct orderProduct) {
      OrderProductDto orderProductDto = mapper.map(orderProduct, OrderProductDto.class);

      orderProductDto.setProductDto(convertToProductDto(orderProduct.getProduct()));

      return orderProductDto;
   }

   public OrderDto convertToOrderDto(Order order) {
      OrderDto orderDto = mapper.map(order, OrderDto.class);

      orderDto.setOrderProductsDto(convertToOrderProductsDto(order.getOrderProducts()));

      return orderDto;
   }

//   public EmployeeDto convertToEmployeeDto(Employee employee) {
//      EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
//
//      employeeDto.setOrdersDto(convertToOrdersDto(employee.getOrders()));
//
//      return employeeDto;
//   }
//
//   public List<EmployeeDto> convertToEmployeesDto(List<Employee> employees) {
//      return employees.stream()
//              .map(this::convertToEmployeeDto)
//              .collect(Collectors.toList());
//   }

   public List<OrderDto> convertToOrdersDto(List<Order> orders) {
      return orders.stream()
              .map(this::convertToOrderDto)
              .collect(Collectors.toList());
   }

   public List<OrderProductDto> convertToOrderProductsDto(List<OrderProduct> orderProducts) {
      return orderProducts.stream()
              .map(this::convertToOrderProductDto)
              .collect(Collectors.toList());
   }
}
