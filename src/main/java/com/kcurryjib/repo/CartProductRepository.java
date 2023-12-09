package com.kcurryjib.repo;

import com.kcurryjib.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
}
