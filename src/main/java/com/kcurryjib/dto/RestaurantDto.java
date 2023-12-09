package com.kcurryjib.dto;

import java.util.Objects;

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

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      RestaurantDto that = (RestaurantDto) o;
      return id == that.id && isOpen == that.isOpen && Objects.equals(name, that.name) &&
              Objects.equals(address, that.address) && Objects.equals(phoneNumber, that.phoneNumber) &&
              Objects.equals(openingHours, that.openingHours) && Objects.equals(cuisineType, that.cuisineType) &&
              Objects.equals(description, that.description) && Objects.equals(socialMediaLinks, that.socialMediaLinks);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, name, address, phoneNumber, openingHours, cuisineType, description, socialMediaLinks, isOpen);
   }

   @Override
   public String toString() {
      return "RestaurantDto{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", address='" + address + '\'' +
              ", phoneNumber='" + phoneNumber + '\'' +
              ", openingHours='" + openingHours + '\'' +
              ", cuisineType='" + cuisineType + '\'' +
              ", description='" + description + '\'' +
              ", socialMediaLinks='" + socialMediaLinks + '\'' +
              ", isOpen=" + isOpen +
              '}';
   }
}
