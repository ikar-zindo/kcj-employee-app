package com.kcurryjib.repo;

import com.kcurryjib.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;


public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

   UserDetails findByUsername(String username);
}
