package com.kcurryjib.dto;

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
}
