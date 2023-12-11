package com.kcurryjib.mapper;

import com.kcurryjib.dto.RestaurantDto;
import com.kcurryjib.entity.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantMapper {

   @Autowired
   private ModelMapper mapper;

   public RestaurantDto convertToRestaurantDto(Restaurant restaurant) {
      RestaurantDto restaurantDto = mapper.map(restaurant, RestaurantDto.class);

      return restaurantDto;
   }

   public List<RestaurantDto> convertToRestaurantsDto(List<Restaurant> restaurants) {
      return restaurants.stream()
              .map(this::convertToRestaurantDto)
              .collect(Collectors.toList());
   }
}
