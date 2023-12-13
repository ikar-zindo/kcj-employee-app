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

   @Autowired
   private OrderMapper orderMapper;

   // convert to DTO
   public EmployeeDto convertToEmployeeDto(Employee employee) {
      EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);

      employeeDto.setOrdersDto(orderMapper.convertToOrdersDto(employee.getOrders()));

      return employeeDto;
   }

   public List<EmployeeDto> convertToEmployeesDto(List<Employee> employees) {
      return employees.stream()
              .map(this::convertToEmployeeDto)
              .collect(Collectors.toList());
   }

   // convert to entity
   public Employee convertToEmployee(EmployeeDto employeeDto) {
      Employee employee = mapper.map(employeeDto, Employee.class);

      return employee;
   }

   public List<Employee> convertToEmployees(List<EmployeeDto> employeesDto) {
      return employeesDto.stream()
              .map(this::convertToEmployee)
              .collect(Collectors.toList());
   }
}
