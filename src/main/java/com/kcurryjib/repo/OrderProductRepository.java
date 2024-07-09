package com.kcurryjib.repo;

import com.kcurryjib.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface OrderProductRepository extends JpaRepository<OrderProduct, UUID> {
}
