package com.kcurryjib.service.admin;

import com.kcurryjib.config.MapperUtil;
import com.kcurryjib.dto.ProductDto;
import com.kcurryjib.entity.Product;
import com.kcurryjib.mapper.admin.ProductMapper;
import com.kcurryjib.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

   @Autowired
   private ProductRepository productRepository;

   @Autowired
   private ProductMapper productMapper;

   public List<ProductDto> gatAll() {
      List<Product> products = new ArrayList<>(productRepository.findAll());

      return MapperUtil.convertlist(products, productMapper::showProductDetails);
   }
}
