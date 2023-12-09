package com.kcurryjib.repo;

import com.kcurryjib.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartRepository extends JpaRepository<Cart, Long> {
}
