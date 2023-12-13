package com.kcurryjib.service.admin;

import com.kcurryjib.config.MapperUtil;
import com.kcurryjib.dto.OrderProductDto;
import com.kcurryjib.entity.OrderProduct;
import com.kcurryjib.mapper.admin.OrderProductMapper;
import com.kcurryjib.repo.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderProductService {

   @Autowired
   private OrderProductRepository orderProductRepository;

   @Autowired
   private OrderProductMapper orderProductMapper;

   public List<OrderProductDto> getAll() {
      List<OrderProduct> orderProducts = new ArrayList<>(orderProductRepository.findAll());

      return MapperUtil.convertlist(orderProducts, orderProductMapper::convertToOrderProductDto);
   }
}
