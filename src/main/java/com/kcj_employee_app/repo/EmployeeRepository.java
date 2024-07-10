package com.kcj_employee_app.repo;

import com.kcj_employee_app.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;


public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

   UserDetails findByUsername(String username);
}
