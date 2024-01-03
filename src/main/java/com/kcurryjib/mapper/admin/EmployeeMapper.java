package com.kcurryjib.mapper.admin;

import com.kcurryjib.dto.EmployeeDto;
import com.kcurryjib.dto.RestaurantDto;
import com.kcurryjib.entity.Employee;
import com.kcurryjib.entity.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

   @Autowired
   private ModelMapper mapper;

   // convert to DTO
   public EmployeeDto convertToEmployeeDto(Employee employee) {
      return mapper.map(employee, EmployeeDto.class);
   }

   // restaurant convert to restaurantDto
   public RestaurantDto showRestaurantDetails(Restaurant restaurant) {
      return mapper.map(restaurant, RestaurantDto.class);
   }

   public EmployeeDto convertToEmployeeDtoWithRestaurantDto(Employee employee) {
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
   public Employee convertToEmployeeWithRestaurant(EmployeeDto employeeDto) {
      Employee employee = mapper.map(employeeDto, Employee.class);

      employee.setRestaurant(convertToRestaurant(employeeDto.getRestaurantDto()));

      return employee;
   }

   // restaurant convert to restaurantDto
   public Restaurant convertToRestaurant(RestaurantDto restaurantDto) {
      return mapper.map(restaurantDto, Restaurant.class);
   }

   public List<Employee> convertToEmployee(List<EmployeeDto> employeesDto) {
      return employeesDto.stream()
              .map(this::convertToEmployeeWithRestaurant)
              .collect(Collectors.toList());
   }
}
