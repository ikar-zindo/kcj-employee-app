package com.kcj_employee_app.service.admin;

import com.kcj_employee_app.config.MapperUtil;
import com.kcj_employee_app.dto.EmployeeDto;
import com.kcj_employee_app.dto.RestaurantDto;
import com.kcj_employee_app.entity.Employee;
import com.kcj_employee_app.entity.Restaurant;
import com.kcj_employee_app.entity.enums.Role;
import com.kcj_employee_app.exception.list.EmployeeException;
import com.kcj_employee_app.mapper.admin.EmployeeMapper;
import com.kcj_employee_app.mapper.employee.OrderMapper;
import com.kcj_employee_app.repo.EmployeeRepository;
import com.kcj_employee_app.repo.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService implements UserDetailsService {

   private final EmployeeRepository employeeRepository;

   private final EmployeeMapper employeeMapper;

   private final OrderMapper orderMapper;

   private final RestaurantRepository restaurantRepository;

   private final PasswordEncoder encoder;

   @Autowired
   public EmployeeService(EmployeeRepository employeeRepository,
                          EmployeeMapper employeeMapper,
                          OrderMapper orderMapper,
                          PasswordEncoder encoder,
                          RestaurantRepository restaurantRepository) {

      this.employeeRepository = employeeRepository;
      this.employeeMapper = employeeMapper;
      this.orderMapper = orderMapper;
      this.restaurantRepository = restaurantRepository;
      this.encoder = encoder;
   }

   //READ
   public List<EmployeeDto> getAll() throws EmployeeException {
      List<Employee> employees = new ArrayList<>(employeeRepository.findAll());

      return MapperUtil.convertlist(employees, employeeMapper::showEmployeeInfo);
   }

   // READ
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      UserDetails employee = employeeRepository.findByUsername(username);

      if (employee == null) {
         throw new UsernameNotFoundException("Employee not found!");
      }

      return employee;
   }

   // READ
   public EmployeeDto getEmployeeById(UUID employeeId) throws EmployeeException {
      EmployeeDto employeeDto = null;

      if (employeeId != null) {
         Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

         if (employeeOptional.isPresent()) {
            employeeDto = orderMapper.showEmployeeWithOrders(employeeOptional.get());

         } else {
            throw new EmployeeException(
                    String.format("Employee not found in database with id=%s",
                            employeeId));
         }
      } else {
         throw new EmployeeException("There is no employee ID to search for!");
      }

      return employeeDto;
   }

   // CREATE
   public EmployeeDto addEmployee(EmployeeDto employeeDto) throws EmployeeException {

      if (employeeDto != null && employeeDto.getId() == null) {
         RestaurantDto restaurantDto = employeeDto.getRestaurantDto();

         if (restaurantDto != null && restaurantDto.getId() != null) {
            Restaurant restaurant = restaurantRepository.findById(restaurantDto.getId()).orElse(null);

            if (restaurant != null) {
               Employee employee = employeeMapper.convertToEmployee(employeeDto);

               employee.setRole(Role.ROLE_USER);
               employee.setPassword(encoder.encode(employee.getPassword()));
               employee.setRestaurant(restaurant);
               employee.setCreatedAt(LocalDateTime.now());

               Employee employeeResponse = employeeRepository.save(employee);
               UUID idResponse = employeeResponse.getId();

               if (idResponse != null) {
                  return employeeMapper.convertToEmployeeDto(employeeResponse);

               } else {
                  throw new EmployeeException("Could not create a product in the database");
               }
            } else {
               throw new EmployeeException(
                       String.format("No restaurant found with Id=%d. I can't create a product!",
                               restaurantDto.getId()));
            }
         } else {
            throw new EmployeeException("The ID of the associated restaurant is missing. Product has not been created!");
         }
      } else {
         throw new EmployeeException("Error processing received request body!");
      }
   }

   // CREATE
   public Employee save(Employee employee) throws EmployeeException {
      Employee foundEmployee = (Employee) employeeRepository.findByUsername(employee.getUsername());

      if (foundEmployee != null) {
         return null;
      }

      employee.setRole(Role.ROLE_USER);
      employee.setPassword(encoder.encode(employee.getPassword()));
      employee.setCreatedAt(LocalDateTime.now());

      return employeeRepository.save(employee);
   }

   // UPDATE
   public EmployeeDto updateEmployee(EmployeeDto employeeDto) throws EmployeeException {

      if (employeeDto.getId() != null) {
         Optional<Employee> employeeOptional = employeeRepository.findById(employeeDto.getId());

         RestaurantDto restaurantDto = employeeDto.getRestaurantDto();
         Restaurant restaurant = restaurantRepository.findById(restaurantDto.getId()).orElse(null);

         if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();

            employee.setFirstName(employeeDto.getFirstName());
            employee.setLastName(employeeDto.getLastName());
            employee.setEmail(employeeDto.getEmail());
            employee.setUsername(employeeDto.getUsername());
            employee.setPhoneNumber(employeeDto.getPhoneNumber());

            employee.setRestaurant(restaurant);

            Employee employeeResponse = employeeRepository.save(employee);

            if (employeeResponse != null) {
               return employeeMapper.convertToEmployeeDto(employeeResponse);

            } else {
               throw new EmployeeException(
                       String.format("Failed to update the product in the database with Id=%s!",
                               employeeDto.getId()));
            }
         } else {
            throw new EmployeeException(
                    String.format("The product was not found in the database with Id=%s!",
                            employeeDto.getId()));
         }
      } else {
         throw new EmployeeException("Error processing received body request!");
      }
   }

   //DELETE
   public EmployeeDto blockEmployee(UUID employeeId) throws EmployeeException {

      if (employeeId != null) {
         Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

         if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setActive(false); // isActive = false;

            Employee employeeResponse = employeeRepository.save(employee);

            if (employeeResponse != null) {
               return employeeMapper.convertToEmployeeDto(employeeResponse);

            } else {
               throw new EmployeeException(
                       String.format("Failed to delete employee in database with Id=%s!",
                               employeeId));
            }
         } else {
            throw new EmployeeException(
                    String.format("Employee not found in the database with Id=%s!",
                            employeeId));
         }
      } else {
         throw new EmployeeException("The ID of the employee to be deleted is missing!");
      }
   }
}
