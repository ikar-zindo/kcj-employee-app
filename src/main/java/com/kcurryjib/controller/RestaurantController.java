package com.kcurryjib.controller;

import com.kcurryjib.dto.RestaurantDto;
import com.kcurryjib.entity.Restaurant;
import com.kcurryjib.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurant")
public class RestaurantController {

   @Autowired
   private RestaurantService service;

   @GetMapping
   public ResponseEntity<List<RestaurantDto>> getAll() {
      List<RestaurantDto> restaurants = service.getAll();
      return new ResponseEntity<>(restaurants, HttpStatus.OK);
   }
}
