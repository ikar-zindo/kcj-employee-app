package com.kcj_employee_app.mapper.employee;

import com.kcj_employee_app.dto.EmployeeDto;
import com.kcj_employee_app.dto.OrderDto;
import com.kcj_employee_app.dto.OrderProductDto;
import com.kcj_employee_app.dto.RestaurantDto;
import com.kcj_employee_app.entity.Employee;
import com.kcj_employee_app.entity.Order;
import com.kcj_employee_app.entity.OrderProduct;
import com.kcj_employee_app.entity.Restaurant;
import com.kcj_employee_app.mapper.admin.CustomerMapper;
import com.kcj_employee_app.mapper.admin.ProductMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

   @Autowired
   private ModelMapper mapper;

   @Autowired
   private ProductMapper productMapper;

   @Autowired
   CustomerMapper customerMapper;

   // convert to DTO
   public RestaurantDto convertToRestaurantDto(Restaurant restaurant) {
      return mapper.map(restaurant, RestaurantDto.class);
   }

   public EmployeeDto showEmployeeWithOrders(Employee employee) {
      mapper.typeMap(Employee.class, EmployeeDto.class)
              .addMappings(m -> m.skip(EmployeeDto::setPassword));

      EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);

      employeeDto.setOrdersDto(convertToOrdersDto(employee.getOrders()));

      return employeeDto;
   }

   public OrderDto convertToOrderDto(Order order) {
      OrderDto orderDto = mapper.map(order, OrderDto.class);

      orderDto.setCustomerDto(customerMapper.customerInfoDelivery(order.getCustomer()));
      orderDto.setOrderProductsDto(convertToOrderProductsDto(order.getOrderProducts()));
      orderDto.setRestaurantDto(convertToRestaurantDto(order.getRestaurant()));

      return orderDto;
   }

   public OrderProductDto converToOrderProductDto(OrderProduct orderProduct) {
      OrderProductDto orderProductDto = mapper.map(orderProduct, OrderProductDto.class);

      orderProductDto.setProductDto(productMapper.convertToProductDto(orderProduct.getProduct()));

      return orderProductDto;
   }

   public List<OrderDto> convertToOrdersDto(List<Order> orders) {
      return orders.stream()
              .map(this::convertToOrderDto)
              .collect(Collectors.toList());
   }

   public List<OrderProductDto> convertToOrderProductsDto(List<OrderProduct> orderProducts) {
      return orderProducts.stream()
              .map(this::converToOrderProductDto)
              .collect(Collectors.toList());
   }
}
