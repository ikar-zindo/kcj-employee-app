package com.kcj_employee_app.repo;

import com.kcj_employee_app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
