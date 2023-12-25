package com.kcurryjib.service.admin;

import com.kcurryjib.config.MapperUtil;
import com.kcurryjib.dto.RestaurantDto;
import com.kcurryjib.entity.Restaurant;
import com.kcurryjib.exceptions.ProductException;
import com.kcurryjib.exceptions.RestaurantException;
import com.kcurryjib.mapper.admin.RestaurantMapper;
import com.kcurryjib.repo.EmployeeRepository;
import com.kcurryjib.repo.RestaurantRepository;
import com.kcurryjib.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

   //   @Autowired
   private RestaurantRepository restaurantRepository;

   //   @Autowired
   private RestaurantMapper restaurantMapper;

   private EmployeeRepository employeeRepository;

   private ReviewRepository reviewRepository;

   @Autowired
   public RestaurantService(RestaurantRepository restaurantRepository,
                            RestaurantMapper restaurantMapper,
                            EmployeeRepository employeeRepository,
                            ReviewRepository reviewRepository) {

      this.restaurantRepository = restaurantRepository;
      this.restaurantMapper = restaurantMapper;
      this.employeeRepository = employeeRepository;
      this.reviewRepository = reviewRepository;
   }

   // READ
   public List<RestaurantDto> getAll() throws RestaurantException {
      List<Restaurant> restaurants = new ArrayList<>(restaurantRepository.findAll());

      return MapperUtil.convertlist(restaurants, restaurantMapper::convertToRestaurantDto);
   }


   // READ
   public List<RestaurantDto> fullInfo() throws RestaurantException {
      List<Restaurant> restaurants = new ArrayList<>(restaurantRepository.findAll());

      return MapperUtil.convertlist(restaurants, restaurantMapper::fullEmployeeReview);
   }

   // READ
   public RestaurantDto getById(Long id) throws RestaurantException {
      Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
      RestaurantDto restaurantDto = null;

      if (restaurantOptional.isPresent()) {
         restaurantDto = MapperUtil.convertlist(List.of(restaurantOptional.get()), restaurantMapper::convertToRestaurantDto).get(0);
      }

//      Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
//      RestaurantDto restaurantDto = MapperUtil.convertlist(List.of(restaurant), restaurantMapper::convertToRestaurantDto).get(0);

      return restaurantDto;
   }

   // READ
   public RestaurantDto showWithComments(Long id) throws RestaurantException {
      Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
      RestaurantDto restaurantDto = null;

      if (restaurantOptional.isPresent()) {
         restaurantDto = MapperUtil.convertlist(List.of(restaurantOptional.get()), restaurantMapper::showCustomersWithComments).get(0);
      }



//      Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
//      RestaurantDto restaurantDto = MapperUtil.convertlist(List.of(restaurant), restaurantMapper::convertToRestaurantDto).get(0);

      return restaurantDto;
   }


   // CREATE
   public RestaurantDto addRestaurant(RestaurantDto restaurantDto) throws RestaurantException {

      if (restaurantDto.getName() == null) {
         restaurantDto.setName("new restaurant");
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

      if (restaurantDto.getName() == null) {
         restaurantDto.setName("new restaurant");
      }

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
               throw new ProductException(
                       String.format("Failed to update the restaurant in the database with Id=%d!",
                               restaurantDto.getId()));
            }
         } else {
            throw new ProductException(
                    String.format("The restaurant was not found in the database with Id=%d!",
                            restaurantDto.getId()));
         }
      } else {
         throw new ProductException("Error processing received body request!");
      }
   }

   //DELETE
   public RestaurantDto deleteRestaurant(Long id) throws RestaurantException {

      if (id != null) {
         Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);

         if (restaurantOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();
            restaurant.setOpen(false); // isOpen = false;

            Restaurant restaurantResponse = restaurantRepository.save(restaurant);

            if (restaurantResponse != null) {
               return restaurantMapper.convertToRestaurantDto(restaurantResponse);
            } else {
               throw new ProductException(String.format("Failed to delete restaurant in database with Id=%d!", id));
            }
         } else {
            throw new ProductException(String.format("Restaurant not found in the database with Id=%d!", id));
         }
      } else {
         throw new ProductException("The ID of the restaurant to be deleted is missing!");
      }
   }
}
