package com.kcurryjib.controller.admin;

import com.kcurryjib.dto.RestaurantDto;
import com.kcurryjib.exceptions.ProductException;
import com.kcurryjib.exceptions.RestaurantException;
import com.kcurryjib.service.admin.EmployeeService;
import com.kcurryjib.service.admin.RestaurantService;
import com.kcurryjib.service.admin.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("admin/restaurants")
//@SessionAttributes("editProducts")
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

      return "/admin/restaurants/all";
   }

   // READ
   @GetMapping("/{id}")
   public String getRestaurantById(@PathVariable Long id,
                                   Model model) throws RestaurantException {

      RestaurantDto restaurant = service.getById(id);
      model.addAttribute("restaurant", restaurant);

      return "/admin/restaurants/info";
   }

   // CREATE
//   @GetMapping(value = "/add")
//   public String addRestaurant(@ModelAttribute("restaurant") RestaurantDto restaurantDto,
//                            Model model) throws RestaurantException {
//
//      restaurantDto.setName("new restaurant");
//
//      return "admin/restaurants/add";
//   }
//
//   // CREATE
//   @PostMapping(value = "/add")
//   public String createRestaurant(@ModelAttribute("product") @Valid RestaurantDto restaurantDto,
//                               BindingResult result,
//                               Model model) throws RestaurantException {
//
//
//      if (result.hasErrors()) {
//         model.addAttribute("restaurant", restaurantDto);
//         return "admin/restaurants/add";
//      }
//
//      service.addRestaurant(restaurantDto);
//      return "redirect:/admin/restaurants";
//   }
//
//   // UPDATE
//   @GetMapping(value = "/{id}/edit")
//   public String editRestaurant(@PathVariable(value = "id") Long id,
//                             Model model) throws RestaurantException {
//
//      RestaurantDto restaurantDto = service.getById(id);
//
//      if (restaurantDto == null) {
//         return "redirect:/admin/restaurants";
//      }
//
//      model.addAttribute("restaurant", restaurantDto);
//
//      return "/admin/restaurants/edit";
//   }
//
//   // UPDATE
//   @PostMapping(value = "/{id}/edit")
//   public String updateRestaurant(@ModelAttribute("product") @Valid RestaurantDto restaurantDto,
//                               BindingResult result,
//                               Model model) throws RestaurantException {
//
//
//
//      if (result.hasErrors()) {
//         model.addAttribute("restaurant", restaurantDto);
//         return "admin/restaurants/edit";
//      }
//
//      service.updateRestaurant(restaurantDto);
//      return "redirect:/admin/restaurants";
//   }
//
//   // DELETE
//   @DeleteMapping("/{id}")
//   public String deleteRestaurant(@PathVariable Long id) throws RestaurantException {
//      service.deleteRestaurant(id);
//      return "redirect:/admin/restaurants";
//   }
}
