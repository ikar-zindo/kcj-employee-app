package com.kcurryjib.dto;

import java.util.List;


public class RestaurantDto {

   private long id;

   private String name;

   private String address;

   private String phoneNumber;

   private String openingHours;

   private String cuisineType;

   private String description;

   private String socialMediaLinks;

   private boolean isOpen;

   private List<ProductDto> productsDto;

   private List<ReviewDto> reviewsDto;

   private List<OrderDto> ordersDto;

   private List<EmployeeDto> employeesDto;

   public RestaurantDto() {
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
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
}
