package com.kcurryjib.service.admin;

import com.kcurryjib.config.MapperUtil;
import com.kcurryjib.dto.RestaurantDto;
import com.kcurryjib.entity.Restaurant;
import com.kcurryjib.mapper.admin.RestaurantMapper;
import com.kcurryjib.repo.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

   @Autowired
   private RestaurantRepository restaurantRepository;

   @Autowired
   private RestaurantMapper restaurantMapper;

   public List<RestaurantDto> getAll() {
      List<Restaurant> restaurants = new ArrayList<>(restaurantRepository.findAll());

      return MapperUtil.convertlist(restaurants, restaurantMapper::convertToRestaurantDto);
   }

   public List<RestaurantDto> showWithComments() {
      List<Restaurant> restaurants = new ArrayList<>(restaurantRepository.findAll());

      return MapperUtil.convertlist(restaurants, restaurantMapper::showCustomersWithComments);
   }

   public List<RestaurantDto> fullInfo() {
      List<Restaurant> restaurants = new ArrayList<>(restaurantRepository.findAll());

      return MapperUtil.convertlist(restaurants, restaurantMapper::fullEmployeeReview);
   }

   public RestaurantDto getById(Long id) {
      Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
      RestaurantDto restaurantDto = MapperUtil.convertlist(List.of(restaurant), restaurantMapper::convertToRestaurantDto).get(0);

      return restaurantDto;
   }
}
