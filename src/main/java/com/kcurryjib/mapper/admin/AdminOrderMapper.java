package com.kcurryjib.mapper.admin;

import com.kcurryjib.dto.EmployeeDto;
import com.kcurryjib.dto.OrderDto;
import com.kcurryjib.entity.Employee;
import com.kcurryjib.entity.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminOrderMapper {

   @Autowired
   private ModelMapper mapper;

   @Autowired
   private CustomerMapper customerMapper;


   public OrderDto orderWithoutEmployee(Order order) {
      OrderDto orderDto = mapper.map(order, OrderDto.class);

      orderDto.setCustomerDto(customerMapper.customerInfoDelivery(order.getCustomer()));

      if (order.getEmployee() != null) {
         orderDto.setEmployeeDto(convertToEmployeeDto(order.getEmployee()));
      } else {
         EmployeeDto employeeDto = new EmployeeDto();

         employeeDto.setFirstName("");
         employeeDto.setLastName("");
         employeeDto.setEmail("");

         orderDto.setEmployeeDto(employeeDto);
      }

      return orderDto;
   }

   public OrderDto orderWithEmployee(Order order) {
      OrderDto orderDto = mapper.map(order, OrderDto.class);

      orderDto.setCustomerDto(customerMapper.customerInfoDelivery(order.getCustomer()));
      orderDto.setEmployeeDto(convertToEmployeeDto(order.getEmployee()));

      return orderDto;
   }

   public EmployeeDto convertToEmployeeDto(Employee employee) {
      return mapper.map(employee, EmployeeDto.class);
   }
}
