package com.kcj_employee_app.mapper.admin;

import com.kcj_employee_app.dto.RestaurantDto;
import com.kcj_employee_app.entity.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantMapper {

   @Autowired
   private ModelMapper mapper;

   @Autowired
   private ReviewMapper reviewMapper;

   @Autowired
   private EmployeeMapper employeeMapper;

   // convert to DTO
   public RestaurantDto convertToRestaurantDto(Restaurant restaurant) {
      return mapper.map(restaurant, RestaurantDto.class);
   }

   public RestaurantDto showCustomersWithComments(Restaurant restaurant) {
      RestaurantDto restaurantDto = convertToRestaurantDto(restaurant);

      restaurantDto.setReviewsDto(reviewMapper.convertToReviewsDto(restaurant.getReviews()));

      return restaurantDto;
   }


   public RestaurantDto fullEmployeeReview(Restaurant restaurant) {
      RestaurantDto restaurantDto = convertToRestaurantDto(restaurant);

      restaurantDto.setEmployeesDto(employeeMapper.convertToEmployeesDto(restaurant.getEmployees()));

      return restaurantDto;
   }

   public RestaurantDto RestaurantShort(Restaurant restaurant) {
      return mapper.map(restaurant, RestaurantDto.class);
   }

   // convert to entity
   public Restaurant convertToRestaurant(RestaurantDto restaurantDto) {
      Restaurant restaurant = mapper.map(restaurantDto, Restaurant.class);

      return restaurant;
   }

   public List<Restaurant> convertToRestaurants(List<RestaurantDto> restaurantsDto) {
      return restaurantsDto.stream()
              .map(this::convertToRestaurant)
              .collect(Collectors.toList());
   }
}
