package com.kcurryjib.repo;

import com.kcurryjib.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//   @Override
   UserDetails findByUsername(String username);
}
