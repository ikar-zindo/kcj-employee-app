package com.kcurryjib.mapper.admin;

import com.kcurryjib.dto.ProductDto;
import com.kcurryjib.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

   @Autowired
   private ModelMapper mapper;

   // convert to DTO
   public ProductDto convertToProductDto(Product product) {
      ProductDto productDto = mapper.map(product, ProductDto.class);

      return productDto;
   }

   public List<ProductDto> convertToProductsDto(List<Product> products) {
      return products.stream()
              .map(this::convertToProductDto)
              .collect(Collectors.toList());
   }

   // convert to entity
   public Product convertToProduct(ProductDto productDto) {
      Product product = mapper.map(productDto, Product.class);

      return product;
   }

   public List<Product> convertToProducts(List<ProductDto> productsDto) {
      return productsDto.stream()
              .map(this::convertToProduct)
              .collect(Collectors.toList());
   }
}
