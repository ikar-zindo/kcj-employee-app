package com.kcj_employee_app.mapper.admin;

import com.kcj_employee_app.dto.RestaurantDto;
import com.kcj_employee_app.dto.ReviewDto;
import com.kcj_employee_app.entity.Restaurant;
import com.kcj_employee_app.entity.Review;
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

      reviewDto.setCustomerDto(customerMapper.shortCustomerDto(review.getCustomer()));
      reviewDto.setRestaurantDto(showRestaurantDetails(review.getRestaurant()));

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
