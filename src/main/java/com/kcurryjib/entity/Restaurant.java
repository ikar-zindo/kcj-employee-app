package com.kcurryjib.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "restaurant")
public class Restaurant {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "restaurant_id")
   private Long id;

   @Column(name = "name")
   private String name;

   @Column(name = "address")
   private String address;

   @Column(name = "phone_number")
   private String phoneNumber;

   @Column(name = "opening_hours")
   private String openingHours;

   @Column(name = "cuisine_type")
   private String cuisineType;

   @Column(name = "description")
   private String description;

   @Column(name = "social_media_links")
   private String socialMediaLinks;

   @Column(name = "is_open")
   private boolean isOpen;

   @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
   private List<Product> products;

   @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
   private List<Review> reviews;

   @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
   private List<Order> orders;

   @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
   private List<Employee> employees;

   public Restaurant() {
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

   public List<Product> getProducts() {
      return products;
   }

   public void setProducts(List<Product> products) {
      this.products = products;
   }

   public List<Review> getReviews() {
      return reviews;
   }

   public void setReviews(List<Review> reviews) {
      this.reviews = reviews;
   }

   public List<Order> getOrders() {
      return orders;
   }

   public void setOrders(List<Order> orders) {
      this.orders = orders;
   }

   public List<Employee> getEmployees() {
      return employees;
   }

   public void setEmployees(List<Employee> employees) {
      this.employees = employees;
   }

}
