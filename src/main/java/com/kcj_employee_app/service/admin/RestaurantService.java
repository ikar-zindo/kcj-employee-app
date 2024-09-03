package com.kcj_employee_app.service.admin;

import com.kcj_employee_app.config.MapperUtil;
import com.kcj_employee_app.dto.RestaurantDto;
import com.kcj_employee_app.dto.ReviewDto;
import com.kcj_employee_app.entity.Restaurant;
import com.kcj_employee_app.exception.list.RestaurantException;
import com.kcj_employee_app.mapper.admin.RestaurantMapper;
import com.kcj_employee_app.repo.EmployeeRepository;
import com.kcj_employee_app.repo.RestaurantRepository;
import com.kcj_employee_app.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

   private RestaurantRepository restaurantRepository;

   private RestaurantMapper restaurantMapper;

   private EmployeeRepository employeeRepository;

   private ReviewRepository reviewRepository;

   private ReviewService reviewService;

   @Autowired
   public RestaurantService(RestaurantRepository restaurantRepository,
                            RestaurantMapper restaurantMapper,
                            EmployeeRepository employeeRepository,
                            ReviewRepository reviewRepository,
                            ReviewService reviewService) {

      this.restaurantRepository = restaurantRepository;
      this.restaurantMapper = restaurantMapper;
      this.employeeRepository = employeeRepository;
      this.reviewRepository = reviewRepository;
      this.reviewService = reviewService;
   }

   // READ
   public List<RestaurantDto> getAll() throws RestaurantException {
      List<Restaurant> restaurants = new ArrayList<>(restaurantRepository.findAll());

      return MapperUtil.convertlist(restaurants, restaurantMapper::convertToRestaurantDto);
   }


   // READ
   public List<RestaurantDto> fullInfo() throws RestaurantException {
      List<Restaurant> restaurants = new ArrayList<>(restaurantRepository.findAll());

      return MapperUtil.convertlist(restaurants, restaurantMapper::convertToRestaurantDto);
   }

   // READ
   public RestaurantDto getById(Long id) throws RestaurantException {
      RestaurantDto restaurantDto = null;

      if (id != null) {
         Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);

         if (restaurantOptional.isPresent()) {
            restaurantDto = restaurantMapper.convertToRestaurantDto(restaurantOptional.get());

         } else {
            throw new RestaurantException(
                    String.format("Restaurant not found in database with id=%d",
                            id));
         }
      } else {
         throw new RestaurantException("There is no restaurant ID to search for!");
      }


      return restaurantDto;
   }

   // READ
   public RestaurantDto showWithComments(Long id) throws RestaurantException {
      Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
      RestaurantDto restaurantDto = null;

      if (restaurantOptional.isPresent()) {
         restaurantDto = restaurantMapper.showCustomersWithComments(restaurantOptional.get());
      }

      return restaurantDto;
   }

   // CREATE
   public RestaurantDto addRestaurant(RestaurantDto restaurantDto) throws RestaurantException {

      if (restaurantDto == null) {
//         restaurantDto.setName("new restaurant");

         throw new RestaurantException("Received null request body for restaurant creation");
      }

      if (restaurantDto != null && restaurantDto.getId() == null) {
         Restaurant restaurant = restaurantMapper.convertToRestaurant(restaurantDto);

         Restaurant restaurantResponse = restaurantRepository.save(restaurant);
         Long idResponse = restaurantResponse.getId();

         if (idResponse != null && idResponse > 0) {
            return restaurantMapper.convertToRestaurantDto(restaurantResponse);

         } else {
            throw new RestaurantException("Could not create a restaurant in the database");
         }
      } else {
         throw new RestaurantException("Error processing received request body!");
      }
   }

   // UPDATE
   public RestaurantDto updateRestaurant(RestaurantDto restaurantDto) throws RestaurantException {

      if (restaurantDto.getId() != null) {
         Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantDto.getId());

         if (restaurantOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();

            restaurant.setName(restaurantDto.getName());
            restaurant.setAddress(restaurantDto.getAddress());
            restaurant.setPhoneNumber(restaurantDto.getPhoneNumber());
            restaurant.setOpeningHours(restaurantDto.getOpeningHours());
            restaurant.setCuisineType(restaurantDto.getCuisineType());
            restaurant.setDescription(restaurantDto.getDescription());
            restaurant.setSocialMediaLinks(restaurantDto.getSocialMediaLinks());
            restaurant.setOpen(restaurantDto.isOpen());

            Restaurant restaurantResponse = restaurantRepository.save(restaurant);

            if (restaurantResponse != null) {
               return restaurantMapper.convertToRestaurantDto(restaurantResponse);

            } else {
               throw new RestaurantException(
                       String.format("Failed to update the restaurant in the database with Id=%d!",
                               restaurantDto.getId()));
            }
         } else {
            throw new RestaurantException(
                    String.format("The restaurant was not found in the database with Id=%d!",
                            restaurantDto.getId()));
         }
      } else {
         throw new RestaurantException("Error processing received body request!");
      }
   }

   //DELETE
   public RestaurantDto closeRestaurant(Long id) throws RestaurantException {

      if (id != null) {
         Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);

         if (restaurantOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();
            restaurant.setOpen(false); // isOpen = false;

            Restaurant restaurantResponse = restaurantRepository.save(restaurant);

            if (restaurantResponse != null) {
               return restaurantMapper.convertToRestaurantDto(restaurantResponse);

            } else {
               throw new RestaurantException(
                       String.format("Failed to delete restaurant in database with Id=%d!",
                               id));
            }
         } else {
            throw new RestaurantException(
                    String.format("Restaurant not found in the database with Id=%d!",
                            id));
         }
      } else {
         throw new RestaurantException("The ID of the restaurant to be deleted is missing!");
      }
   }

   // Aggregation
   public int getNumberOfReviewsByRestaurantId(Long id) throws RestaurantException {
      RestaurantDto restaurantDto = showWithComments(id);

      if (restaurantDto != null && restaurantDto.getReviewsDto() != null) {
         return restaurantDto.getReviewsDto().size();
      }
      return 0;
   }

   public BigDecimal getAverageRatingByRestaurantId(Long id) throws RestaurantException {
      RestaurantDto restaurantDto = showWithComments(id);

      if (restaurantDto != null && restaurantDto.getReviewsDto() != null &&
              !restaurantDto.getReviewsDto().isEmpty()) {

         BigDecimal sum = BigDecimal.ZERO;
         int numberOfReviews = restaurantDto.getReviewsDto().size(); // save count reviews

         for (ReviewDto review : restaurantDto.getReviewsDto()) {
            BigDecimal rating = review.getRating();

            if (rating != null) { // check rating is not null
               sum = sum.add(rating);
            }
         }

         if (numberOfReviews != 0) { // check that the number of reviews is not equal to avoid division by zero
            return sum.divide(BigDecimal.valueOf(numberOfReviews), 1, RoundingMode.HALF_UP);
         }
      }

      return BigDecimal.ZERO;
   }
}
