package com.kcurryjib.service;

import com.kcurryjib.config.MapperUtil;
import com.kcurryjib.dto.RestaurantDto;
import com.kcurryjib.entity.Restaurant;
import com.kcurryjib.mapper.Mappers;
import com.kcurryjib.repo.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

   @Autowired
   private RestaurantRepository repository;

   @Autowired
   private Mappers mappers;

   public List<RestaurantDto> getAll() {
      List<Restaurant> restaurants = new ArrayList<>();
      restaurants.addAll(repository.findAll());

      List<RestaurantDto> restaurantsDto = MapperUtil.convertlist(restaurants, mappers::convertToRestaurantDto);

      return restaurantsDto;
   }
}
