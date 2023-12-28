package com.kcurryjib.aspect.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLoggingRestaurant {

   private final Logger LOGGER = LoggerFactory.getLogger(AspectLoggingRestaurant.class);

   /**
    * Logger for admin.service.RestaurantService.addRestaurant()
    */
   @Pointcut("execution(* com.kcurryjib.service.admin.RestaurantService.addRestaurant(..))")
   public void addRestaurant() {
   }

   /**
    * logging after returning calling addRestaurant()
    */
   @AfterReturning("addRestaurant()")
   public void afterAddRestaurant(JoinPoint joinPoint) {
      Object[] args = joinPoint.getArgs();
      LOGGER.info("Added new restaurant with parameter {}", args[0]);
   }

   /**
    * logging if addRestaurant() throwing exception
    */
   @AfterThrowing(pointcut = "addRestaurant()", throwing = "exception")
   public void afterAddRestaurantException(Exception exception) {
      LOGGER.error(exception.getMessage());
   }
}
