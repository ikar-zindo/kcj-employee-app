package com.kcurryjib.aspect.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLoggingRestaurant {

   private final Logger LOGGER = LoggerFactory.getLogger(AspectLoggingRestaurant.class);

   /**
    * LOGGER for admin.service.RestaurantService.addRestaurant()
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
      LOGGER.info("Add new restaurant with parameter {}", args[0]);
   }

   /**
    * logging if addRestaurant() throwing exception
    */
   @AfterThrowing(pointcut = "addRestaurant()", throwing = "exception")
   public void afterAddRestaurantException(Exception exception) {
      LOGGER.error(exception.getMessage());
   }

   /**
    * LOGGER for admin.service.RestaurantService.updateRestaurant()
    */
   @Pointcut("execution(* com.kcurryjib.service.admin.RestaurantService.updateRestaurant(..))")
   public void updateRestaurant() {
   }

   /**
    * logging after returning calling addRestaurant()
    */
   @AfterReturning("updateRestaurant()")
   public void afterUpdateRestaurant(JoinPoint joinPoint) {
      Object[] args = joinPoint.getArgs();
      LOGGER.info("Update restaurant with parameter {}", args[0]);
   }

   /**
    * logging if addRestaurant() throwing exception
    */
   @AfterThrowing(pointcut = "updateRestaurant()", throwing = "exception")
   public void afterUpdateRestaurantException(Exception exception) {
      LOGGER.error(exception.getMessage());
   }

   /**
    * LOGGER for admin.service.RestaurantService.deleteRestaurant()
    */
   @Pointcut("execution(* com.kcurryjib.service.admin.RestaurantService.closeRestaurant(..))")
   public void deleteRestaurant() {
   }

   /**
    * logging after returning calling addRestaurant()
    */
   @AfterReturning("deleteRestaurant()")
   public void afterDeleteRestaurant(JoinPoint joinPoint) {
      Object[] args = joinPoint.getArgs();
      LOGGER.info("Delete restaurant with parameter {}", args);
   }

   /**
    * logging if addRestaurant() throwing exception
    */
   @AfterThrowing(pointcut = "deleteRestaurant()", throwing = "exception")
   public void afterDeleteRestaurantException(Exception exception) {
      LOGGER.error(exception.getMessage());
   }
}
