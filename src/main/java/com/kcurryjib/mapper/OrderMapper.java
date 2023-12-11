package com.kcurryjib.mapper;

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

   public OrderDto convertToOrderDto(Order order ) {
      OrderDto orderDto = mapper.map(order, OrderDto.class);

      return orderDto;
   }

   public List<OrderDto> convertToOrdersDto(List<Order> orders) {
      return orders.stream()
              .map(this::convertToOrderDto)
              .collect(Collectors.toList());
   }
}
