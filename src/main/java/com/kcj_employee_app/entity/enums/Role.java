package com.kcj_employee_app.entity.enums;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

   ROLE_CUSTOMER("ROLE_CUSTOMER"),
   ROLE_ADMIN("ROLE_ADMIN"),
   ROLE_MANAGER("ROLE_MANAGER"),
   ROLE_USER("ROLE_USER"),

   // SOON
   ROLE_DEALER("ROLE_DEALER"),
   ROLE_DRIVER("ROLE_DRIVER");

   private final String value;

   Role(String value) {
      this.value = value;
   }

   @Override
   public String getAuthority() {
      return value;
   }
}
