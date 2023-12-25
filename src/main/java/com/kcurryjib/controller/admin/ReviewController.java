package com.kcurryjib.controller.admin;

//import com.kcurryjib.dto.RestaurantDto;
//import com.kcurryjib.service.admin.RestaurantService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/admin/reviews")
//public class ReviewController {
//
//   @Autowired
//   private RestaurantService service;
//
//   @GetMapping
//   public String getRestaurantsWithReviews(Model model) {
//      List<RestaurantDto> restaurantsWithReviews = service.showWithComments();
//      model.addAttribute("restaurants", restaurantsWithReviews);
//      return "admin/reviews/all"; // Имя HTML-файла (без расширения) для отображения
//   }
//}
