package com.kcurryjib.mapper.admin;

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

   @Autowired
   private ProductMapper productMapper;

   // convert to DTO

   public OrderProductDto convertToOrderProductDto(OrderProduct orderProduct) {
      OrderProductDto orderProductDto = mapper.map(orderProduct, OrderProductDto.class);

      orderProductDto.setProductDto(productMapper.convertToProductDto(orderProduct.getProduct()));

      return orderProductDto;
   }

   public List<OrderProductDto> convertToOrderProductsDto(List<OrderProduct> orderProducts) {
      return orderProducts.stream()
              .map(this::convertToOrderProductDto)
              .collect(Collectors.toList());
   }

   // convert to entity
   public OrderProduct convertToOrderProduct(OrderProductDto orderProductDto) {
      OrderProduct orderProduct = mapper.map(orderProductDto, OrderProduct.class);

      return orderProduct;
   }

   public List<OrderProduct> convertToOrderProducts(List<OrderProductDto> orderProductsDto) {
      return orderProductsDto.stream()
              .map(this::convertToOrderProduct)
              .collect(Collectors.toList());
   }
}
