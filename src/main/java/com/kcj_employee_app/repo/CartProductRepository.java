package com.kcj_employee_app.repo;

import com.kcj_employee_app.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface CartProductRepository extends JpaRepository<CartProduct, UUID> {
}
