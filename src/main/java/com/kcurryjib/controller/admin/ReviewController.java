package com.kcurryjib.controller.admin;

import com.kcurryjib.dto.CustomerDto;
import com.kcurryjib.dto.ProductDto;
import com.kcurryjib.dto.RestaurantDto;
import com.kcurryjib.dto.ReviewDto;
import com.kcurryjib.exceptions.ReviewException;
import com.kcurryjib.service.admin.CustomerService;
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

      ReviewDto reviewDto = service.getById(id);

      model.addAttribute("review", reviewDto);
      model.addAttribute("restaurant", reviewDto.getRestaurantDto());
      model.addAttribute("customer", reviewDto.getCustomerDto());

      return "/admin/reviews/info";
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
                               @RequestParam(name = "customerId") Long customerId,
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

      return "/admin/reviews/edit";
   }

   // UPDATE
   @PostMapping(value = "/{id}/edit")
   public String updateReview(@ModelAttribute("review") @Valid ReviewDto reviewDto,
                               BindingResult result,
                               @RequestParam(name = "restaurantId") Long restaurantId,
                               @RequestParam(name = "customerId") Long customerId,
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
   public String deleteReview(@PathVariable Long id) throws ReviewException {
      service.deleteReview(id);
      return "redirect:/admin/reviews";
   }
}
