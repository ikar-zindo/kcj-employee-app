package com.kcurryjib.mapper;

import com.kcurryjib.dto.ReviewDto;
import com.kcurryjib.entity.Review;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewMapper {

   @Autowired
   private ModelMapper mapper;

   // convert to DTO
   public ReviewDto convertToReviewDto(Review review) {
      ReviewDto reviewDto = mapper.map(review, ReviewDto.class);

      return reviewDto;
   }

   public List<ReviewDto> convertToReviewsDto(List<Review> reviews) {
      return reviews.stream()
              .map(this::convertToReviewDto)
              .collect(Collectors.toList());
   }

   // convert to entity
   public Review convertToReview(ReviewDto reviewDto) {
      Review review = mapper.map(reviewDto, Review.class);

      return review;
   }

   public List<Review> convertToReviews(List<ReviewDto> reviewsDto) {
      return reviewsDto.stream()
              .map(this::convertToReview)
              .collect(Collectors.toList());
   }
}
