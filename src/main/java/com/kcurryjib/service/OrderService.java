package com.kcurryjib.service;

import com.kcurryjib.config.MapperUtil;
import com.kcurryjib.dto.OrderDto;
import com.kcurryjib.entity.Order;
import com.kcurryjib.mapper.CustomerMapper;
import com.kcurryjib.mapper.OrderMapper;
import com.kcurryjib.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

   @Autowired
   private OrderRepository orderRepository;

   @Autowired
   private OrderMapper orderMapper;

   public List<OrderDto> getAll() {
      List<Order> orders =new ArrayList<>(orderRepository.findAll());

      return MapperUtil.convertlist(orders, orderMapper::convertToOrderDto);
   }
}
