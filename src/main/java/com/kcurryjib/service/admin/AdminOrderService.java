package com.kcurryjib.service.admin;

import com.kcurryjib.config.MapperUtil;
import com.kcurryjib.dto.OrderDto;
import com.kcurryjib.entity.Order;
import com.kcurryjib.entity.enums.OrderStatus;
import com.kcurryjib.exception.list.OrderException;
import com.kcurryjib.mapper.admin.AdminOrderMapper;
import com.kcurryjib.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
      List<Order> orders = new ArrayList<>(orderRepository.findAll()).stream()
              .sorted(Comparator.comparing(Order::getCreatedAt).reversed())
              .collect(Collectors.toList());

      return MapperUtil.convertlist(orders, adminOrderMapper::orderWithoutEmployee);
   }

   public Map<DayOfWeek, Integer> calculateOrdersByDayOfWeekForLastWeek() {
      Map<DayOfWeek, Integer> ordersByDayOfWeek = new LinkedHashMap<>();

      LocalDateTime currentDate = LocalDateTime.now();
      LocalDateTime oneWeekAgo = currentDate.minusWeeks(1);

      List<Order> orders = orderRepository.findByCreatedAtBetweenAndOrderStatus(oneWeekAgo, currentDate, OrderStatus.COMPLETED);

      for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
         ordersByDayOfWeek.put(dayOfWeek, 0);
      }

      for (Order order : orders) {
         DayOfWeek orderDayOfWeek = order.getCreatedAt().getDayOfWeek();
         int currentCount = ordersByDayOfWeek.get(orderDayOfWeek);
         ordersByDayOfWeek.put(orderDayOfWeek, currentCount + 1);
      }

      return ordersByDayOfWeek;
   }
}
