package com.kcurryjib.service.admin;

import com.kcurryjib.config.MapperUtil;
import com.kcurryjib.dto.EmployeeDto;
import com.kcurryjib.entity.Employee;
import com.kcurryjib.entity.enums.Role;
import com.kcurryjib.exception.list.EmployeeException;
import com.kcurryjib.mapper.admin.EmployeeMapper;
import com.kcurryjib.repo.EmployeeRepository;
import com.kcurryjib.repo.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService /*implements UserDetailsService*/ {

   private EmployeeRepository employeeRepository;

   private EmployeeMapper employeeMapper;

   private RestaurantRepository restaurantRepository;


   @Autowired
   public EmployeeService(EmployeeRepository employeeRepository,
                          EmployeeMapper employeeMapper,
                          RestaurantRepository restaurantRepository) {

      this.employeeRepository = employeeRepository;
      this.employeeMapper = employeeMapper;
      this.restaurantRepository = restaurantRepository;
   }

   //READ
   public List<EmployeeDto> getAll() throws EmployeeException {
      List<Employee> employees = new ArrayList<>(employeeRepository.findAll());

      return MapperUtil.convertlist(employees, employeeMapper::showEmployeeWithRestaurant);
   }

//   READ
   public List<Employee> getAllEntity() {
      List<Employee> employees = new ArrayList<>(employeeRepository.findAll());

      return employees;
   }
}
