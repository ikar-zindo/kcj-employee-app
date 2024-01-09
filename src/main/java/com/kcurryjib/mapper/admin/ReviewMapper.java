package com.kcurryjib.mapper.admin;

import com.kcurryjib.dto.RestaurantDto;
import com.kcurryjib.dto.ReviewDto;
import com.kcurryjib.entity.Restaurant;
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

   @Autowired
   private CustomerMapper customerMapper;

//   @Autowired
//   private RestaurantMapper restaurantMapper;

   // convert to DTO
   public ReviewDto convertToReviewDto(Review review) {
      return mapper.map(review, ReviewDto.class);
   }

   public RestaurantDto showRestaurantDetails(Restaurant restaurant) {
      return mapper.map(restaurant, RestaurantDto.class);
   }

   public ReviewDto showReviewDtoWithCustomer(Review review) {
      ReviewDto reviewDto = mapper.map(review, ReviewDto.class);

//      reviewDto.setRestaurantDto(showRestaurantDetails(review.getRestaurant()));
      reviewDto.setCustomerDto(customerMapper.convertToCustomerDto(review.getCustomer()));

      return reviewDto;
   }

   public List<ReviewDto> convertToReviewsDto(List<Review> reviews) {
      return reviews.stream()
              .map(this::showReviewDtoWithCustomer)
              .collect(Collectors.toList());
   }

   // convert to entity
   public Review convertToReview(ReviewDto reviewDto) {
      return mapper.map(reviewDto, Review.class);
   }

   public List<Review> convertToReviews(List<ReviewDto> reviewsDto) {
      return reviewsDto.stream()
              .map(this::convertToReview)
              .collect(Collectors.toList());
   }
}
