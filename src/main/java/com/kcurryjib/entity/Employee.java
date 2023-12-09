package com.kcurryjib.entity;

import com.kcurryjib.entity.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "employee_id")
   private long id;


   @Column(name = "first_name")
   @Pattern(regexp = "[A-Z][a-z]{1,49}", message = "a string should start with a capital letter (rest lowercase) and contain at least two letters")
   private String firstName;

   @Column(name = "last_name")
   @Pattern(regexp = "[A-Z][a-z]{1,49}", message = "a string should start with a capital letter (rest lowercase) and contain at least two letters")
   private String lastName;

   @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Email is not valid")
   @Column(name = "email")
   private String email;

   @Column(name = "nickname")
   private String nickname;

   @Enumerated(EnumType.STRING)
   @Column(name = "role")
   @NotBlank
   private Role role;

   @Column(name = "password")
   private String password;

   @NotBlank(message = "Phone cant be empty")
   @Pattern(regexp = "\\+\\d{8,15}", message = "Phone is not valid")
   @Column(name = "phone_number")
   private String phoneNumber;

   @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
   private LocalDateTime createdAt;

   @Column(name = "is_active")
   private boolean isActive;

   public Employee() {
   }

   public Employee(String firstName, String lastName, String email,
                   String nickname, Role role, String password, String phoneNumber) {

      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.nickname = nickname;
      this.role = role;
      this.password = password;
      this.phoneNumber = phoneNumber;

      isActive = true;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
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

   public String getNickname() {
      return nickname;
   }

   public void setNickname(String nickname) {
      this.nickname = nickname;
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

   public boolean isActive() {
      return isActive;
   }

   public void setActive(boolean active) {
      isActive = active;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Employee employee = (Employee) o;
      return id == employee.id && isActive == employee.isActive && Objects.equals(firstName, employee.firstName) &&
              Objects.equals(lastName, employee.lastName) && Objects.equals(email, employee.email) &&
              Objects.equals(nickname, employee.nickname) && role == employee.role &&
              Objects.equals(password, employee.password) && Objects.equals(phoneNumber, employee.phoneNumber) &&
              Objects.equals(createdAt, employee.createdAt);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, firstName, lastName, email, nickname, role, password, phoneNumber, createdAt, isActive);
   }

   @Override
   public String toString() {
      return "Employee{" +
              "id=" + id +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", email='" + email + '\'' +
              ", nickname='" + nickname + '\'' +
              ", role=" + role +
              ", password='" + password + '\'' +
              ", phoneNumber='" + phoneNumber + '\'' +
              ", createdAt=" + createdAt +
              ", isActive=" + isActive +
              '}';
   }
}
