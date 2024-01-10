package com.kcurryjib.repo;

import com.kcurryjib.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

   UserDetails findByUsername(String username);
}
