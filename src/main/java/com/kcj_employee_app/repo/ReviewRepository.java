package com.kcj_employee_app.repo;

import com.kcj_employee_app.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review, Long> {
}
