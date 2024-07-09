package com.kcurryjib.repo;

import com.kcurryjib.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;


public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
