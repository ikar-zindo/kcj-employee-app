package com.kcurryjib.repo;

import com.kcurryjib.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface CartRepository extends JpaRepository<Cart, UUID> {
}
