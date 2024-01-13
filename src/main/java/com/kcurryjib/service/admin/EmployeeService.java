package com.kcurryjib.service.admin;

import com.kcurryjib.config.MapperUtil;
import com.kcurryjib.dto.EmployeeDto;
import com.kcurryjib.entity.Employee;
import com.kcurryjib.entity.enums.Role;
import com.kcurryjib.exception.list.EmployeeException;
import com.kcurryjib.mapper.admin.AdminEmployeeMapper;
import com.kcurryjib.repo.EmployeeRepository;
import com.kcurryjib.repo.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService implements UserDetailsService {

   private EmployeeRepository employeeRepository;

   private AdminEmployeeMapper adminEmployeeMapper;

   private RestaurantRepository restaurantRepository;

//   @Autowired
   private PasswordEncoder encoder;

   @Autowired
   public EmployeeService(EmployeeRepository employeeRepository,
                          AdminEmployeeMapper adminEmployeeMapper,
//                          PasswordEncoder encoder,
                          RestaurantRepository restaurantRepository) {

      this.employeeRepository = employeeRepository;
      this.adminEmployeeMapper = adminEmployeeMapper;
      this.restaurantRepository = restaurantRepository;
//      this.encoder = encoder;
   }

   //READ
   public List<EmployeeDto> getAll() throws EmployeeException {
      List<Employee> employees = new ArrayList<>(employeeRepository.findAll());

      return MapperUtil.convertlist(employees, adminEmployeeMapper::showEmployeeWithRestaurant);
   }

//   READ
   public List<Employee> getAllEntity() {
      List<Employee> employees = new ArrayList<>(employeeRepository.findAll());

      return employees;
   }

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      UserDetails employee = employeeRepository.findByUsername(username);

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

      employee.setRole(Role.ROLE_USER);
      employee.setPassword(encoder.encode(employee.getPassword()));
      employee.setCreatedAt(LocalDateTime.now());

      return employeeRepository.save(employee);
   }
}
