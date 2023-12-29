package com.kcurryjib.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

   USER("USER"),
   MANAGER("MANAGER"),
   ADMIN("ADMIN"),
   DEALER("DEALER"),
   DRIVER("DRIVER");

   private final String value;

   Role(String value) {
      this.value = value;
   }

   @Override
   public String getAuthority() {
      return value;
   }
}
