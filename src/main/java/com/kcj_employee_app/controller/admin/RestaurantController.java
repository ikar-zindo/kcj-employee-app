package com.kcj_employee_app.controller.admin;

import com.kcj_employee_app.dto.RestaurantDto;
import com.kcj_employee_app.dto.ReviewDto;
import com.kcj_employee_app.exception.list.RestaurantException;
import com.kcj_employee_app.service.admin.EmployeeService;
import com.kcj_employee_app.service.admin.RestaurantService;
import com.kcj_employee_app.service.admin.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/admin/restaurants")
@SessionAttributes("editRestaurants")
public class RestaurantController {

   private final RestaurantService service;

   private final EmployeeService employeeService;

   private final ReviewService reviewService;

   @Autowired
   public RestaurantController(RestaurantService service,
                               EmployeeService employeeService,
                               ReviewService reviewService) {

      this.service = service;
      this.employeeService = employeeService;
      this.reviewService = reviewService;
   }


   // READ
   @GetMapping
   public String getAllRestaurants(Model model) throws RestaurantException {
      List<RestaurantDto> restaurantsDto = service.getAll();

      model.addAttribute("restaurants", restaurantsDto);

      return "admin/restaurants/all";
   }

   // READ
   @GetMapping("/{id}")
   public String getRestaurantById(@PathVariable Long id,
                                   Model model) throws RestaurantException {

      if (service.getById(id) == null) {
         return "redirect:/admin/restaurants";
      }

      RestaurantDto restaurantDto = service.showWithComments(id);
      List<ReviewDto> reviewsDto = restaurantDto.getReviewsDto();
      int countComments = service.getNumberOfReviewsByRestaurantId(id);
      BigDecimal avgRating = service.getAverageRatingByRestaurantId(id);

      model.addAttribute("restaurant", restaurantDto);
      model.addAttribute("countComments", countComments);
      model.addAttribute("avgRating", avgRating);
      model.addAttribute("reviews", reviewsDto);

      return "admin/restaurants/info";
   }

   // CREATE
   @GetMapping(value = "/add")
   @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
   public String addRestaurant(@ModelAttribute("restaurant") RestaurantDto restaurantDto,
                               Model model) throws RestaurantException {

      restaurantDto.setName("new restaurant");

      return "admin/restaurants/add";
   }

   // CREATE
   @PostMapping(value = "/add")
   @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
   public String createRestaurant(@ModelAttribute("restaurant") @Valid RestaurantDto restaurantDto,
                                  BindingResult result,
                                  Model model) throws RestaurantException {


      if (result.hasErrors()) {
         model.addAttribute("restaurant", restaurantDto);
         return "admin/restaurants/add";
      }

      service.addRestaurant(restaurantDto);
      return "redirect:/admin/restaurants";
   }

   // UPDATE
   @GetMapping(value = "/{id}/edit")
   @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
   public String editRestaurant(@PathVariable(value = "id") Long id,
                                Model model) throws RestaurantException {

      RestaurantDto restaurantDto = service.getById(id);

      if (restaurantDto == null) {
         return "redirect:/admin/restaurants";
      }

      model.addAttribute("restaurant", restaurantDto);

      return "admin/restaurants/edit";
   }

   // UPDATE
   @PatchMapping(value = "/{id}/edit")
   @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
   public String updateRestaurant(@ModelAttribute("product") @Valid RestaurantDto restaurantDto,
                                  BindingResult result,
                                  Model model) throws RestaurantException {

      if (result.hasErrors()) {
         model.addAttribute("restaurant", restaurantDto);
         return "admin/restaurants/edit";
      }

      service.updateRestaurant(restaurantDto);
      return "redirect:/admin/restaurants";
   }

   // DELETE
   @PatchMapping("/{id}")
   @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
   public String closeRestaurant(@PathVariable Long id) throws RestaurantException {
      service.closeRestaurant(id);
      return "redirect:/admin/restaurants";
   }
}
