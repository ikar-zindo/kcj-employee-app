package com.kcurryjib.mapper;

import com.kcurryjib.dto.OrderProductDto;
import com.kcurryjib.entity.OrderProduct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderProductMapper {

   @Autowired
   private ModelMapper mapper;

   public OrderProductDto convertToOrderProductDto(OrderProduct orderProduct) {
      OrderProductDto orderProductDto = mapper.map(orderProduct, OrderProductDto.class);

      return orderProductDto;
   }

   public List<OrderProductDto> convertToOrderProductsDto(List<OrderProduct> orderProducts) {
      return orderProducts.stream()
              .map(this::convertToOrderProductDto)
              .collect(Collectors.toList());
   }
}
