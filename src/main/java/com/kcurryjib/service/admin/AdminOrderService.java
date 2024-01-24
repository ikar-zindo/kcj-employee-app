package com.kcurryjib.service.admin;

import com.kcurryjib.config.MapperUtil;
import com.kcurryjib.dto.OrderDto;
import com.kcurryjib.entity.Order;
import com.kcurryjib.exception.list.OrderException;
import com.kcurryjib.mapper.admin.AdminOrderMapper;
import com.kcurryjib.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminOrderService {

   private final OrderRepository orderRepository;

   private final AdminOrderMapper adminOrderMapper;

   @Autowired
   public AdminOrderService(OrderRepository orderRepository,
                            AdminOrderMapper adminOrderMapper) {

      this.orderRepository = orderRepository;
      this.adminOrderMapper = adminOrderMapper;
   }

   // READ
   public List<OrderDto> getAll() throws OrderException {
      List<Order> orders = new ArrayList<>(orderRepository.findAll());

      return MapperUtil.convertlist(orders, adminOrderMapper::convertToOrderDto);
   }
}
