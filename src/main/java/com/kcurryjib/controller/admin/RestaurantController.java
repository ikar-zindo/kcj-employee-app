package com.kcurryjib.controller.admin;

import com.kcurryjib.dto.RestaurantDto;
import com.kcurryjib.service.admin.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/restaurant")
public class RestaurantController {

   @Autowired
   private RestaurantService service;

   @GetMapping
   public ResponseEntity<List<RestaurantDto>> getAll() {
      List<RestaurantDto> restaurantsDto = service.getAll();
      return new ResponseEntity<>(restaurantsDto, HttpStatus.OK);
   }

   @GetMapping("/full")
   ResponseEntity<List<RestaurantDto>> fullEmployeeReview() {
      List<RestaurantDto> restaurantsDto = service.fullEmployeeReview();

      return new ResponseEntity<>(restaurantsDto, HttpStatus.OK);
   }
}
