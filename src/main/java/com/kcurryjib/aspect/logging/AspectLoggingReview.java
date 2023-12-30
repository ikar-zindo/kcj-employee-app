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
public class AspectLoggingReview {

   private final Logger LOGGER = LoggerFactory.getLogger(AspectLoggingRestaurant.class);

   /**
    * LOGGER for admin.service.ReviewService.addReview()
    */
   @Pointcut("execution(* com.kcurryjib.service.admin.ReviewService.addReview(..))")
   public void addReview() {}

   /**
    * logging after returning calling addReview()
    */
   @AfterReturning("addReview()")
   public void afterAddRestaurant(JoinPoint joinPoint) {
      Object[] args = joinPoint.getArgs();
      LOGGER.info("Add new review with parameter {}", args[0]);
   }

   /**
    * logging if addReview() throwing exception
    */
   @AfterThrowing(pointcut = "addReview()", throwing = "exception")
   public void afterAddRestaurantException(Exception exception) {
      LOGGER.error(exception.getMessage());
   }

   /**
    * LOGGER for admin.service.ReviewService.updateReview()
    */
   @Pointcut("execution(* com.kcurryjib.service.admin.ReviewService.updateReview(..))")
   public void updateReview() {}

   /**
    * logging after returning calling updateReview()
    */
   @AfterReturning("updateReview()")
   public void afterUpdateReview(JoinPoint joinPoint) {
      Object[] args = joinPoint.getArgs();
      LOGGER.info("Update review  with parameter {}", args[0]);
   }

   /**
    * logging if updateReview() throwing exception
    */
   @AfterThrowing(pointcut = "addReview()", throwing = "exception")
   public void afterUpdateReviewException(Exception exception) {
      LOGGER.error(exception.getMessage());
   }

   /**
    * LOGGER for admin.service.ReviewService.deleteReview()
    */
   @Pointcut("execution(* com.kcurryjib.service.admin.ReviewService.deleteReview(..))")
   public void deleteReview() {}

   /**
    * logging after returning calling addReview()
    */
   @AfterReturning("deleteReview()")
   public void afterDeleteReview(JoinPoint joinPoint) {
      Object[] args = joinPoint.getArgs();
      LOGGER.warn("Delet review with parameter {}", args);
   }

   /**
    * logging if addReview() throwing exception
    */
   @AfterThrowing(pointcut = "deleteReview()", throwing = "exception")
   public void afterDeleteReviewException(Exception exception) {
      LOGGER.error(exception.getMessage());
   }
}
