package com.kcurryjib.security.model;

import com.kcurryjib.entity.enums.Role;

//@MappedSuperclass
public class UserDto {

   /**
    * The firstName (login) of the user.
    */
   String firstName;

   /**
    * The lastName (login) of the user.
    */
   String lastName;

   /**
    * The phoneNumber (login) of the user.
    */
   String phoneNumber;

   /**
    * The email (login) of the user.
    */
   String email;

   /**
    * The username (login) of the user.
    */
   private String login;

   /**
    * The password of the user.
    */
   private String password;

   /**
    * The role assigned to the user.
    */
   private Role role;

   // Constructors

   public UserDto() {
   }

   public UserDto(String firstName, String lastName, String phoneNumber,
                  String email, String login, String password, Role role) {

      this.firstName = firstName;
      this.lastName = lastName;
      this.phoneNumber = phoneNumber;
      this.email = email;
      this.login = login;
      this.password = password;
      this.role = role;
   }

   // Getters & Setters
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

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getLogin() {
      return login;
   }

   public void setLogin(String login) {
      this.login = login;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Role getRole() {
      return role;
   }

   public void setRole(Role role) {
      this.role = role;
   }

   // Builder
   public static class Builder {

      private UserDto userDto = new UserDto();

      public Builder firstName(String firstName) {
         userDto.firstName = firstName;
         return this;
      }
      public Builder lastName(String lastName) {
         userDto.lastName = lastName;
         return this;
      }
      public Builder phoneNumber(String phoneNumber) {
         userDto.phoneNumber = phoneNumber;
         return this;
      }
      public Builder email(String email) {
         userDto.email = email;
         return this;
      }
      public Builder login(String login) {
         userDto.login = login;
         return this;
      }
      public Builder password(String password) {
         userDto.password = password;
         return this;
      }
      public Builder role(Role role) {
         userDto.role = role;
         return this;
      }

      public UserDto build() {
         return userDto;
      }
   }

   public static Builder builder() {
      return new Builder();
   }
}
