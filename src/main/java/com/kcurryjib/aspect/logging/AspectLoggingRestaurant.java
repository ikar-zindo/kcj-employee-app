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
    * Logger for package admin/service class RestaurantService
    */
   @Pointcut("execution(* com.kcurryjib.service.admin.RestaurantService.addRestaurant(..))")
   public void addRestaurantService() {}

   /**
    * afterReturning calling addRestaurant()
    */
   @AfterReturning("addRestaurantService()")
   public void afterAddRestaurant(JoinPoint joinPoint) {
      Object[] args = joinPoint.getArgs();
      LOGGER.info("Added new restaurant with parameter {}", args[0]);
   }

   @AfterThrowing("addRestaurantService()")
   public void afterThrowingException() {
      LOGGER.warn("Error processing received request body!");
   }

   @Pointcut("execution(* com.kcurryjib.controller.admin.RestaurantController.createRestaurant(..))")
   public void addRestaurantController() {}

   @AfterThrowing("addRestaurantController()")
   public void afterThrowingExceptionController() {
      LOGGER.warn("Error processing received request body!");
   }
}
