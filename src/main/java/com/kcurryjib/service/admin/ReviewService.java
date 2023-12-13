package com.kcurryjib.service.admin;

import com.kcurryjib.config.MapperUtil;
import com.kcurryjib.dto.ReviewDto;
import com.kcurryjib.entity.Review;
import com.kcurryjib.mapper.admin.ReviewMapper;
import com.kcurryjib.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

   @Autowired
   private ReviewRepository reviewRepository;

   @Autowired
   private ReviewMapper reviewMapper;

   public List<ReviewDto> getAll() {
      List<Review> reviews = new ArrayList<>(reviewRepository.findAll());

      return MapperUtil.convertlist(reviews, reviewMapper::convertToReviewDto);
   }
}
