package com.kcurryjib.mapper.admin;

import com.kcurryjib.dto.ProductDto;
import com.kcurryjib.dto.RestaurantDto;
import com.kcurryjib.entity.Product;
import com.kcurryjib.entity.Restaurant;
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
      return mapper.map(product, ProductDto.class);
   }

   public ProductDto showProductDetails(Product product) {
      ProductDto productDto = mapper.map(product, ProductDto.class);

      productDto.setRestaurantDto(showRestaurantDetails(product.getRestaurant()));

      return productDto;
   }

   public RestaurantDto showRestaurantDetails(Restaurant restaurant) {
      return mapper.map(restaurant, RestaurantDto.class);
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
