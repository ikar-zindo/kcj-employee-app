package com.kcj_employee_app.aspect.logging;

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
public class AspectLoggingProduct {

   private final Logger LOGGER = LoggerFactory.getLogger(AspectLoggingProduct.class);

   /**
    * LOGGER for admin.service.ProductService.addProduct()
    */
   @Pointcut("execution(* com.kcj_employee_app.service.admin.ProductService.addProduct(..))")
   public void addProduct() {
   }

   /**
    * logging after returning calling addRestaurant()
    */
   @AfterReturning("addProduct()")
   public void afterAddRestaurant(JoinPoint joinPoint) {
      Object[] args = joinPoint.getArgs();
      LOGGER.info("Add new product with parameter {}", args[0]);
   }

   /**
    * logging if addRestaurant() throwing exception
    */
   @AfterThrowing(pointcut = "addProduct()", throwing = "exception")
   public void afterThrowingException(Exception exception) {
      LOGGER.error(exception.getMessage());
   }

   /**
    * LOGGER for admin.service.ProductService.updateProduct()
    */
   @Pointcut("execution(* com.kcj_employee_app.service.admin.ProductService.updateProduct(..))")
   public void updateProduct() {
   }

   /**
    * logging after returning calling updateProduct()
    */
   @AfterReturning("updateProduct()")
   public void afterUpdateProduct(JoinPoint joinPoint) {
      Object[] args = joinPoint.getArgs();
      LOGGER.info("Update product with parameter {}", args[0]);
   }

   /**
    * logging if updateProduct() throwing exception
    */
   @AfterThrowing(pointcut = "updateProduct()", throwing = "exception")
   public void afterUpdateProductException(Exception exception) {
      LOGGER.error(exception.getMessage());
   }

   /**
    * LOGGER for admin.service.ProductService.deleteProduct()
    */
   @Pointcut("execution(* com.kcj_employee_app.service.admin.ProductService.blockProduct(..))")
   public void deleteProduct() {
   }

   /**
    * logging after returning calling deleteProduct()
    */
   @AfterReturning("deleteProduct()")
   public void afterDeleteProduct(JoinPoint joinPoint) {
      Object[] args = joinPoint.getArgs();
      LOGGER.warn("Delete product with parameter {}", args);
   }

   /**
    * logging if deleteProduct() throwing exception
    */
   @AfterThrowing(pointcut = "deleteProduct()", throwing = "exception")
   public void afterDeleteProductException(Exception exception) {
      LOGGER.error(exception.getMessage());
   }
}
