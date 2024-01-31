package com.kcurryjib.repo;

import com.kcurryjib.entity.Order;
import com.kcurryjib.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {

   List<Order> findByCreatedAtBetweenAndOrderStatus(LocalDateTime startDate, LocalDateTime endDate, OrderStatus orderStatus);
}
