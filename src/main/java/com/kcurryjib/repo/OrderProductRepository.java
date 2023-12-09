package com.kcurryjib.repo;

import com.kcurryjib.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
