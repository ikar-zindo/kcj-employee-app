package com.kcurryjib.mapper;

import com.kcurryjib.dto.EmployeeDto;
import com.kcurryjib.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

   @Autowired
   private ModelMapper mapper;

   public EmployeeDto convertToEmployeeDto(Employee employee) {
      EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);

      return employeeDto;
   }

   public List<EmployeeDto> convertToEmployeesDto(List<Employee> employees) {
      return employees.stream()
              .map(this::convertToEmployeeDto)
              .collect(Collectors.toList());
   }
}
