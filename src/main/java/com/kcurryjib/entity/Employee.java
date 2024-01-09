package com.kcurryjib.entity;

import com.kcurryjib.entity.enums.Role;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee /*implements UserDetails*/ {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "employee_id")
   private Long id;

   @Column(name = "first_name")
   private String firstName;

   @Column(name = "last_name")
   private String lastName;

   @Column(name = "email")
   private String email;

   @Column(name = "nickname", unique = true)
   private String username;

   @Enumerated(EnumType.STRING)
   @Column(name = "role")
   private Role role;

   @Column(name = "password")
   private String password;

   @Column(name = "phone_number")
   private String phoneNumber;

   @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
   private LocalDateTime createdAt;

   @Column(name = "is_active")
   private Boolean isActive;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "restaurant_id")
   private Restaurant restaurant;

   @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
   private List<Order> orders;

   public Employee() {
   }

   // Getters & Setters
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public Role getRole() {
      return role;
   }

   public void setRole(Role role) {
      this.role = role;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public LocalDateTime getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(LocalDateTime createdAt) {
      this.createdAt = createdAt;
   }

   public Boolean getActive() {
      return isActive;
   }

   public void setActive(Boolean active) {
      isActive = active;
   }

   public Restaurant getRestaurant() {
      return restaurant;
   }

   public void setRestaurant(Restaurant restaurant) {
      this.restaurant = restaurant;
   }

   public List<Order> getOrders() {
      return orders;
   }

   public void setOrders(List<Order> orders) {
      this.orders = orders;
   }


//   @Override
//   public Collection<? extends GrantedAuthority> getAuthorities() {
//      return AuthorityUtils.createAuthorityList(
////              String.valueOf(role.getAuthority()));
//              String.valueOf(this.role));
//   }



   // Equals & HashCode
//   @Override
//   public boolean equals(Object o) {
//      if (this == o) return true;
//      if (o == null || getClass() != o.getClass()) return false;
//      Employee employee = (Employee) o;
//      return Objects.equals(id, employee.id) && Objects.equals(firstName, employee.firstName) &&
//              Objects.equals(lastName, employee.lastName) && Objects.equals(email, employee.email) &&
//              Objects.equals(username, employee.username) && role == employee.role &&
//              Objects.equals(password, employee.password) && Objects.equals(phoneNumber, employee.phoneNumber) &&
//              Objects.equals(createdAt, employee.createdAt) && Objects.equals(isActive, employee.isActive) &&
//              Objects.equals(restaurant, employee.restaurant) && Objects.equals(orders, employee.orders);
//   }
//
//   @Override
//   public int hashCode() {
//      return Objects.hash(id, firstName, lastName, email, username, role, password,
//              phoneNumber, createdAt, isActive, restaurant, orders);
//   }

   // ToString
//   @Override
//   public String toString() {
//      return "Employee{" +
//              "id=" + id +
//              ", firstName='" + firstName + '\'' +
//              ", lastName='" + lastName + '\'' +
//              ", email='" + email + '\'' +
//              ", username='" + username + '\'' +
//              ", role=" + role +
//              ", password='" + password + '\'' +
//              ", phoneNumber='" + phoneNumber + '\'' +
//              ", createdAt=" + createdAt +
//              ", isActive=" + isActive +
//              ", restaurant=" + restaurant +
//              ", orders=" + orders +
//              '}';
//   }

   // Builder class
   public static class Builder {

      private Employee employee = new Employee();

      public Builder id(Long id) {
         employee.id = id;
         return this;
      }

      public Builder firstName(String firstName) {
         employee.firstName = firstName;
         return this;
      }

      public Builder lastName(String lastName) {
         employee.lastName = lastName;
         return this;
      }

      public Builder email(String email) {
         employee.email = email;
         return this;
      }

      public Builder username(String username) {
         employee.username = username;
         return this;
      }

      public Builder role(Role role) {
         employee.role = role;
         return this;
      }

      public Builder password(String password) {
         employee.password = password;
         return this;
      }

      public Builder phoneNumber(String phoneNumber) {
         employee.phoneNumber = phoneNumber;
         return this;
      }

      public Builder createdAt(LocalDateTime createdAt) {
         employee.createdAt = createdAt;
         return this;
      }

      public Builder isActive(Boolean isActive) {
         employee.isActive = isActive;
         return this;
      }

      public Builder restaurant(Restaurant restaurant) {
         employee.restaurant = restaurant;
         return this;
      }

      public Employee build() {
         return employee;
      }
   }

   public static Builder builder() {
      return new Builder();
   }
}
