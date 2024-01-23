package com.kcurryjib.entity.enums;

public enum OrderStatus {

   CREATED("CREATED"),
   PROCESSING("PROCESSING"),
   COMPLETED("COMPLETED"),
   CANCELLED("CANCELLED");

   public String value;

   OrderStatus(String value) {
      this.value = value;
   }

   public String getColor(OrderStatus status) {
      switch (status) {
         case CREATED:
            return "primary";
         case PROCESSING:
            return "warning";
         case COMPLETED:
            return "success";
         case CANCELLED:
            return "danger";
         default:
            return "secondary";
      }
   }
}
