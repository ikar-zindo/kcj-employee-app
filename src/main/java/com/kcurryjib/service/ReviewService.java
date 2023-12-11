package com.kcurryjib.service;

import com.kcurryjib.config.MapperUtil;
import com.kcurryjib.dto.ReviewDto;
import com.kcurryjib.entity.Review;
import com.kcurryjib.mapper.Mappers;
import com.kcurryjib.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

   @Autowired
   private ReviewRepository repository;

   @Autowired
   private Mappers mappers;

   public List<ReviewDto> getAll() {
      List<Review> reviews = new ArrayList<>();
      repository.findAll().forEach(reviews::add);

      List<ReviewDto> reviewsDto = MapperUtil.convertlist(reviews, mappers::convertToReviewDto);

      return reviewsDto;
   }
}
