package com.kcj_employee_app.repo;

import com.kcj_employee_app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
