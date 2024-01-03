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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService implements UserDetailsService {

   private EmployeeRepository employeeRepository;

   private EmployeeMapper employeeMapper;

//   private RestaurantRepository restaurantRepository;

   private BCryptPasswordEncoder encoder;

   @Autowired
   public EmployeeService(EmployeeRepository employeeRepository,
                          EmployeeMapper employeeMapper,
//                          RestaurantRepository restaurantRepository,
                          BCryptPasswordEncoder encoder) {

      this.employeeRepository = employeeRepository;
      this.employeeMapper = employeeMapper;
//      this.restaurantRepository = restaurantRepository;
      this.encoder = encoder;
   }

   //READ
   public List<EmployeeDto> getAll() throws EmployeeException {
      List<Employee> employees = new ArrayList<>(employeeRepository.findAll());

      return MapperUtil.convertlist(employees, employeeMapper::convertToEmployeeDtoWithRestaurantDto);
   }

   //READ
   public List<Employee> getAllEntity() {
      List<Employee> employees = new ArrayList<>(employeeRepository.findAll());

      return employees;
   }

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      UserDetails employee = employeeRepository.findByUsername(username); // Исправить на findByUsername

      if (employee == null) {
         throw new UsernameNotFoundException("Employee not found!");
      }

      return employee;
   }

   public Employee save(Employee employee) {
      Employee foundEmployee = (Employee) employeeRepository.findByUsername(employee.getUsername());

      if (foundEmployee != null) {
         return null;
      }

      Role role = Role.ROLE_USER;

      employee.setRole(role);
      employee.setPassword(encoder.encode(employee.getPassword()));

      return employeeRepository.save(employee);
   }
}
