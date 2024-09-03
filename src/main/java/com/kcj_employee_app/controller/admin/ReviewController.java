package com.kcj_employee_app.controller.admin;

import com.kcj_employee_app.dto.CustomerDto;
import com.kcj_employee_app.dto.RestaurantDto;
import com.kcj_employee_app.dto.ReviewDto;
import com.kcj_employee_app.exception.list.ReviewException;
import com.kcj_employee_app.service.admin.CustomerService;
import com.kcj_employee_app.service.admin.RestaurantService;
import com.kcj_employee_app.service.admin.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/reviews")
@SessionAttributes("editReviews")
public class ReviewController {

   private final ReviewService service;

   private final RestaurantService restaurantService;

   private final CustomerService customerService;

   @Autowired
   public ReviewController(ReviewService service,
                           RestaurantService restaurantService,
                           CustomerService customerService) {

      this.service = service;
      this.restaurantService = restaurantService;
      this.customerService = customerService;
   }

   // READ
   @GetMapping
   public String getReviews(Model model) {
      List<ReviewDto> reviewsDto = service.getAll();

      model.addAttribute("reviews", reviewsDto);

      return "admin/reviews/all";
   }

   // READ
   @GetMapping("/{id}")
   public String getReviewById(@PathVariable Long id,
                               Model model) throws ReviewException {

      if (service.getById(id) == null) {
         return "redirect:/admin/reviews";
      }

      ReviewDto reviewDto = service.getById(id);

      model.addAttribute("review", reviewDto);
      model.addAttribute("restaurant", reviewDto.getRestaurantDto());
      model.addAttribute("customer", reviewDto.getCustomerDto());

      return "admin/reviews/info";
   }

   // CREATE
   @GetMapping(value = "/add")
   public String addReview(@ModelAttribute("review") ReviewDto reviewDto,
                           Model model) throws ReviewException {

      model.addAttribute("restaurants", restaurantService.getAll());
      model.addAttribute("customers", customerService.getAll());

      return "admin/reviews/add";
   }

   // CREATE
   @PostMapping(value = "/add")
   public String createReview(@ModelAttribute("review") @Valid ReviewDto reviewDto,
                              BindingResult result,
                              @RequestParam(name = "restaurantId") Long restaurantId,
                              @RequestParam(name = "customerId") UUID customerId,
                              Model model) throws ReviewException {

      RestaurantDto restaurantDto = restaurantService.getById(restaurantId);
      CustomerDto customerDto = customerService.getById(customerId);

      reviewDto.setRestaurantDto(restaurantDto);
      reviewDto.setCustomerDto(customerDto);

      if (result.hasErrors()) {
         model.addAttribute("review", reviewDto);
         model.addAttribute("restaurants", restaurantService.getAll());
         model.addAttribute("customers", customerService.getAll());

         return "admin/reviews/add";
      }

      service.addReview(reviewDto);
      return "redirect:/admin/reviews";
   }

   // UPDATE
   @GetMapping(value = "/{id}/edit")
   public String editReview(@PathVariable(value = "id") Long id,
                            Model model) throws ReviewException {

      ReviewDto reviewDto = service.getById(id);

      if (reviewDto == null) {
         return "redirect:/admin/reviews";
      }

      model.addAttribute("review", reviewDto);
      model.addAttribute("restaurants", restaurantService.getAll());
      model.addAttribute("customers", customerService.getAll());

      return "admin/reviews/edit";
   }

   // UPDATE
   @PatchMapping(value = "/{id}/edit")
   public String updateReview(@ModelAttribute("review") @Valid ReviewDto reviewDto,
                              BindingResult result,
                              @RequestParam(name = "restaurantId") Long restaurantId,
                              @RequestParam(name = "customerId") UUID customerId,
                              Model model) throws ReviewException {

      RestaurantDto restaurantDto = restaurantService.getById(restaurantId);
      CustomerDto customerDto = customerService.getById(customerId);

      reviewDto.setRestaurantDto(restaurantDto);
      reviewDto.setCustomerDto(customerDto);


      if (result.hasErrors()) {
         model.addAttribute("review", reviewDto);
         model.addAttribute("restaurants", restaurantService.getAll());
         model.addAttribute("customers", customerService.getAll());

         return "admin/reviews/edit";
      }

      service.updateReview(reviewDto);

      return "redirect:/admin/reviews";
   }

   // DELETE
   @PostMapping("/{id}/remove")
   @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
   public String deleteReview(@PathVariable Long id) throws ReviewException {
      service.deleteReview(id);
      return "redirect:/admin/reviews";
   }
}
