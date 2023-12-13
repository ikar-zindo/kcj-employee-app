package com.kcurryjib.mapper.admin;

import com.kcurryjib.dto.OrderDto;
import com.kcurryjib.entity.Order;
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
   private OrderProductMapper orderProductMapper;

   @Autowired
   private CustomerMapper customerMapper;

   // convert to DTO
   public OrderDto convertToOrderDto(Order order) {
      OrderDto orderDto = mapper.map(order, OrderDto.class);

      orderDto.setOrderProductsDto(orderProductMapper.convertToOrderProductsDto(order.getOrderProducts()));
      orderDto.setCustomerDto(customerMapper.convertToCustomerDto(order.getCustomer()));

      return orderDto;
   }

   public List<OrderDto> convertToOrdersDto(List<Order> orders) {
      return orders.stream()
              .map(this::convertToOrderDto)
              .collect(Collectors.toList());
   }

   // convert to entity
   public Order convertToOrder(OrderDto orderDto) {
      Order order = mapper.map(orderDto, Order.class);

      return order;
   }

   public List<Order> convertToOrders(List<OrderDto> ordersDto) {
      return ordersDto.stream()
              .map(this::convertToOrder)
              .collect(Collectors.toList());
   }

}
