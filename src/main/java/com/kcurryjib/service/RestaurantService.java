package com.kcurryjib.service;

import com.kcurryjib.entity.Restaurant;
import com.kcurryjib.repo.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

   @Autowired
   RestaurantRepository repository;

   public List<Restaurant> getAll() {
      return new ArrayList<>(repository.findAll());
   }
}
