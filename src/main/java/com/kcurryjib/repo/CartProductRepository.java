package com.kcurryjib.repo;

import com.kcurryjib.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;


public interface CartProductRepository extends JpaRepository<CartProduct, UUID> {
}
