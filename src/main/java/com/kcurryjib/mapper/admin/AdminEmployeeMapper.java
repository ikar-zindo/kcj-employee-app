package com.kcurryjib.mapper.admin;

import com.kcurryjib.dto.EmployeeDto;
import com.kcurryjib.dto.OrderDto;
import com.kcurryjib.dto.OrderProductDto;
import com.kcurryjib.dto.RestaurantDto;
import com.kcurryjib.entity.Employee;
import com.kcurryjib.entity.Order;
import com.kcurryjib.entity.OrderProduct;
import com.kcurryjib.entity.Restaurant;
import com.kcurryjib.exception.list.EmployeeException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AdminEmployeeMapper {

   @Autowired
   private ModelMapper mapper;

   // convert to DTO
   public EmployeeDto convertToEmployeeDto(Employee employee) {
      return mapper.map(employee, EmployeeDto.class);
   }

   public RestaurantDto showRestaurantDetails(Restaurant restaurant) {
      mapper.typeMap(Restaurant.class, RestaurantDto.class)
              .addMappings(m -> m.skip(RestaurantDto::setOrdersDto))
              .addMappings(m -> m.skip(RestaurantDto::setReviewsDto))
              .addMappings(m -> m.skip(RestaurantDto::setEmployeesDto))
              .addMappings(m -> m.skip(RestaurantDto::setProductsDto));

      return mapper.map(restaurant, RestaurantDto.class);
   }

   public EmployeeDto showEmployeeWithRestaurant(Employee employee) {
      mapper.typeMap(Employee.class, EmployeeDto.class)
              .addMappings(m -> m.skip(EmployeeDto::setPassword));

      EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);

      employeeDto.setRestaurantDto(showRestaurantDetails(employee.getRestaurant()));

      return employeeDto;
   }

   public List<EmployeeDto> convertToEmployeesDto(List<Employee> employees) {
      return employees.stream()
              .map(this::convertToEmployeeDto)
              .collect(Collectors.toList());
   }

   // convert to entity
   public Employee convertToEmployee(EmployeeDto employeeDto) {
      return mapper.map(employeeDto, Employee.class);
   }

   public List<Employee> convertToEmployees(List<EmployeeDto> employeesDto) {
      return employeesDto.stream()
              .map(this::convertToEmployee)
              .collect(Collectors.toList());
   }
}
