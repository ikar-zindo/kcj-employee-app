package com.kcj_employee_app.repo;

import com.kcj_employee_app.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface OrderProductRepository extends JpaRepository<OrderProduct, UUID> {
}
