package com.kcurryjib.repo;

import com.kcurryjib.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//   UserDetails findByUsername(String username);
}
