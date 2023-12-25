package com.kcurryjib.mapper.admin;

import com.kcurryjib.dto.EmployeeDto;
import com.kcurryjib.entity.Employee;
import com.kcurryjib.entity.Order;
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
