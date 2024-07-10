package com.kcj_employee_app.service.admin;

import com.kcj_employee_app.config.MapperUtil;
import com.kcj_employee_app.dto.OrderDto;
import com.kcj_employee_app.entity.Order;
import com.kcj_employee_app.entity.enums.OrderStatus;
import com.kcj_employee_app.exception.list.OrderException;
import com.kcj_employee_app.mapper.admin.AdminOrderMapper;
import com.kcj_employee_app.repo.OrderRepository;
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
