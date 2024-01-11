package com.kcurryjib.service.admin;

import com.kcurryjib.config.MapperUtil;
import com.kcurryjib.dto.EmployeeDto;
import com.kcurryjib.entity.Employee;
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

   private RestaurantRepository restaurantRepository;

//   private BCryptPasswordEncoder encoder;

   @Autowired
   public EmployeeService(EmployeeRepository employeeRepository,
                          EmployeeMapper employeeMapper,
//                          BCryptPasswordEncoder encoder,
                          RestaurantRepository restaurantRepository) {

      this.employeeRepository = employeeRepository;
      this.employeeMapper = employeeMapper;
      this.restaurantRepository = restaurantRepository;
//      this.encoder = encoder;
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

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      UserDetails employee = employeeRepository.findByUsername(username);

      if (employee == null) {
         throw new UsernameNotFoundException("Employee not found!");
      }

      return employee;
   }

//   public Employee save(Employee employee) {
//      Employee foundEmployee = (Employee) employeeRepository.findByUsername(employee.getUsername());
//
//      if (foundEmployee != null) {
//         return null;
//      }
//
//      employee.setRole(Role.ROLE_USER);
//      employee.setPassword(encoder.encode(employee.getPassword()));
//      employee.setCreatedAt(LocalDateTime.now());
//
//      return employeeRepository.save(employee);
//   }
}
