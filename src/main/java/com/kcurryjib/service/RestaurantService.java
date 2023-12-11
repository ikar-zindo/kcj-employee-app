package com.kcurryjib.service;

import com.kcurryjib.config.MapperUtil;
import com.kcurryjib.dto.RestaurantDto;
import com.kcurryjib.entity.Restaurant;
import com.kcurryjib.mapper.RestaurantMapper;
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
}
