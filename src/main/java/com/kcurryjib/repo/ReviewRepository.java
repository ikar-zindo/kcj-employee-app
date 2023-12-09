package com.kcurryjib.repo;

import com.kcurryjib.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review, Long> {
}
