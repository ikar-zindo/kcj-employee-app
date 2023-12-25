package com.kcurryjib.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


public class RestaurantDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Long id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Length(max = 30, message = "{validation.restaurant.name.length}")
   private String name;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Length(max = 30, message = "{validation.restaurant.address.length}")
   private String address;

   @JsonInclude(JsonInclude.Include.NON_NULL)
//   @Pattern(regexp = "\\\\+49\\\\d{10}", message = "{validation.restaurant.phoneNumber.length}")
   @Length(max = 20, message = "Max length is 20")
   private String phoneNumber;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Length(max = 30, message = "Max length is 30")
   private String openingHours;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Length(max = 30, message = "Max length is 30")
   private String cuisineType;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Length(max = 1000, message = "{validation.restaurant.description.length}")
   private String description;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Length(max = 200, message = "{validation.restaurant.socialMediaLinks.length}")
   private String socialMediaLinks;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private boolean isOpen;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("products")
   private List<ProductDto> productsDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("reviews")
   private List<ReviewDto> reviewsDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("orders")
   private List<OrderDto> ordersDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("employees")
   private List<EmployeeDto> employeesDto;

   public RestaurantDto() {
   }

   public RestaurantDto(Long id) {
      this.id = id;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public String getOpeningHours() {
      return openingHours;
   }

   public void setOpeningHours(String openingHours) {
      this.openingHours = openingHours;
   }

   public String getCuisineType() {
      return cuisineType;
   }

   public void setCuisineType(String cuisineType) {
      this.cuisineType = cuisineType;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getSocialMediaLinks() {
      return socialMediaLinks;
   }

   public void setSocialMediaLinks(String socialMediaLinks) {
      this.socialMediaLinks = socialMediaLinks;
   }

   public boolean isOpen() {
      return isOpen;
   }

   public void setOpen(boolean open) {
      isOpen = open;
   }

   public List<ProductDto> getProductsDto() {
      return productsDto;
   }

   public void setProductsDto(List<ProductDto> productsDto) {
      this.productsDto = productsDto;
   }

   public List<ReviewDto> getReviewsDto() {
      return reviewsDto;
   }

   public void setReviewsDto(List<ReviewDto> reviewsDto) {
      this.reviewsDto = reviewsDto;
   }

   public List<OrderDto> getOrdersDto() {
      return ordersDto;
   }

   public void setOrdersDto(List<OrderDto> ordersDto) {
      this.ordersDto = ordersDto;
   }

   public List<EmployeeDto> getEmployeesDto() {
      return employeesDto;
   }

   public void setEmployeesDto(List<EmployeeDto> employeesDto) {
      this.employeesDto = employeesDto;
   }

//   public int getNumberOfReviews() {
//      if (reviewsDto != null) {
//         return reviewsDto.size();
//      } else {
//         return 0;
//      }
//   }
//
//   public BigDecimal getAverageRating() {
//      if (reviewsDto != null && !reviewsDto.isEmpty()) {
//         BigDecimal sum = BigDecimal.ZERO;
//         for (ReviewDto review : reviewsDto) {
//            BigDecimal rating = review.getRating();
//            sum = sum.add(rating);
//         }
//         return sum.divide(BigDecimal.valueOf(reviewsDto.size()), 2, RoundingMode.HALF_UP);
//      } else {
//         return BigDecimal.ZERO;
//      }
//   }
}
