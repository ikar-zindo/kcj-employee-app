package com.kcj_employee_app.repo;

import com.kcj_employee_app.entity.Order;
import com.kcj_employee_app.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public interface OrderRepository extends JpaRepository<Order, UUID> {

   List<Order> findByCreatedAtBetweenAndOrderStatus(LocalDateTime startDate, LocalDateTime endDate, OrderStatus orderStatus);
}
