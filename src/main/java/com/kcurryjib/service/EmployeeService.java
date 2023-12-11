package com.kcurryjib.service;

import com.kcurryjib.config.MapperUtil;
import com.kcurryjib.dto.EmployeeDto;
import com.kcurryjib.entity.Employee;
import com.kcurryjib.mapper.EmployeeMapper;
import com.kcurryjib.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

   @Autowired
   private EmployeeRepository employeeRepository;

   @Autowired
   private EmployeeMapper employeeMapper;

   public List<EmployeeDto> getAll() {
      List<Employee> employees = new ArrayList<>(employeeRepository.findAll());

      return MapperUtil.convertlist(employees, employeeMapper::convertToEmployeeDto);
   }
}
