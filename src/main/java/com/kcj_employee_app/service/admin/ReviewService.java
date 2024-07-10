package com.kcj_employee_app.service.admin;

import com.kcj_employee_app.config.MapperUtil;
import com.kcj_employee_app.dto.CustomerDto;
import com.kcj_employee_app.dto.RestaurantDto;
import com.kcj_employee_app.dto.ReviewDto;
import com.kcj_employee_app.entity.Customer;
import com.kcj_employee_app.entity.Restaurant;
import com.kcj_employee_app.entity.Review;
import com.kcj_employee_app.exception.list.ReviewException;
import com.kcj_employee_app.mapper.admin.ReviewMapper;
import com.kcj_employee_app.repo.CustomerRepository;
import com.kcj_employee_app.repo.RestaurantRepository;
import com.kcj_employee_app.repo.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

   private ReviewMapper reviewMapper;

   private ReviewRepository reviewRepository;

   private RestaurantRepository restaurantRepository;

   private CustomerRepository customerRepository;

   //   @Autowired
   public ReviewService(ReviewMapper reviewMapper,
                        ReviewRepository reviewRepository,
                        RestaurantRepository restaurantRepository,
                        CustomerRepository customerRepository) {

      this.reviewMapper = reviewMapper;
      this.reviewRepository = reviewRepository;
      this.restaurantRepository = restaurantRepository;
      this.customerRepository = customerRepository;
   }

   // READ
   public List<ReviewDto> getAll() throws ReviewException {
      List<Review> reviews = new ArrayList<>(reviewRepository.findAll());

      return MapperUtil.convertlist(reviews, reviewMapper::showReviewDtoWithCustomer);
//      return reviewMapper.showReviewDtoWithCustomer(reviews);
   }

   // READ
   public ReviewDto getById(Long id) throws ReviewException {
      ReviewDto reviewDto = null;

      if (id != null) {
         Optional<Review> reviewOptional = reviewRepository.findById(id);

         if (reviewOptional.isPresent()) {
            reviewDto = reviewMapper.showReviewDtoWithCustomer(reviewOptional.get());

         } else {
            throw new ReviewException(
                    String.format("Review not found in database with id=%d",
                            id));
         }
      } else {
         throw new ReviewException("There is no review ID to search for!");
      }

      return reviewDto;
   }

   // CREATE
   public ReviewDto addReview(ReviewDto reviewDto) throws ReviewException {

      if (reviewDto != null && reviewDto.getId() == null) {
         RestaurantDto restaurantDto = reviewDto.getRestaurantDto();
         CustomerDto customerDto = reviewDto.getCustomerDto();

         if (restaurantDto != null && restaurantDto.getId() != null) {
            Restaurant restaurant = restaurantRepository.findById(restaurantDto.getId()).orElse(null);

            if (customerDto != null && customerDto.getId() != null) {
               Customer customer = customerRepository.findById(customerDto.getId()).orElse(null);

               if (restaurant != null) {
                  Review review = reviewMapper.convertToReview(reviewDto);

                  review.setRestaurant(restaurant);
                  review.setCustomer(customer);
                  review.setCreatedAt(LocalDateTime.now());

                  Review reviewResponse = reviewRepository.save(review);
                  Long idResponse = reviewResponse.getId();

                  if (idResponse != null && idResponse > 0) {
                     return reviewMapper.convertToReviewDto(reviewResponse);

                  } else {
                     throw new ReviewException("Could not create a review in the database");
                  }
               } else {
                  throw new ReviewException(String.format("No restaurant found with Id=%d. I can't create a review!", restaurantDto.getId()));
               }
            } else {
               throw new ReviewException("The ID of the associated customer is missing. I can't create a review!");
            }
         } else {
            throw new ReviewException("The ID of the associated restaurant is missing. I can't create a review!");
         }
      } else {
         throw new ReviewException("Error processing received request body!");
      }
   }

   // UPDATE
   public ReviewDto updateReview(ReviewDto reviewDto) throws ReviewException {

      if (reviewDto.getId() != null) {
         Optional<Review> reviewOptional = reviewRepository.findById(reviewDto.getId());

         RestaurantDto restaurantDto = reviewDto.getRestaurantDto();
         Restaurant restaurant = restaurantRepository.findById(restaurantDto.getId()).orElse(null);

         CustomerDto customerDto = reviewDto.getCustomerDto();
         Customer customer = customerRepository.findById(customerDto.getId()).orElse(null);

         if (reviewOptional.isPresent()) {
            Review review = reviewOptional.get();

            review.setRating(reviewDto.getRating());
            review.setComment(reviewDto.getComment());

            review.setRestaurant(restaurant);
            review.setCustomer(customer);

            Review reviewResponse = reviewRepository.save(review);

            if (reviewResponse != null) {
               return reviewMapper.convertToReviewDto(reviewResponse);

            } else {
               throw new ReviewException(
                       String.format("Failed to update the review in the database with Id=%d!",
                               reviewDto.getId()));
            }
         } else {
            throw new ReviewException(
                    String.format("The review was not found in the database with Id=%d!",
                            reviewDto.getId()));
         }
      } else {
         throw new ReviewException("Error processing received body request!");
      }
   }

   //DELETE
   public ReviewDto deleteReview(Long id) throws ReviewException {

      if (id != null) {
         Optional<Review> reviewOptional = reviewRepository.findById(id);

         if (reviewOptional.isPresent()) {
            Review review = reviewOptional.get();
            reviewRepository.deleteById(id);

            if (review != null) {
               return reviewMapper.convertToReviewDto(review);

            } else {
               throw new ReviewException(
                       String.format("Failed to delete review in database with Id=%d!",
                               id));
            }
         } else {
            throw new ReviewException(
                    String.format("Review not found in the database with Id=%d!",
                            id));
         }
      } else {
         throw new ReviewException("The ID of the review to be deleted is missing!");
      }
   }
}
