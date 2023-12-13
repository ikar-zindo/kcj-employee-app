package com.kcurryjib.service.admin;

import com.kcurryjib.config.MapperUtil;
import com.kcurryjib.dto.ProductDto;
import com.kcurryjib.entity.Product;
import com.kcurryjib.exceptions.ProductException;
import com.kcurryjib.mapper.admin.ProductMapper;
import com.kcurryjib.repo.ProductRepository;
import com.kcurryjib.repo.RestaurantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
//   @Autowired
//   private ModelMapper modelMapper;

   private ProductRepository productRepository;

   private RestaurantRepository restaurantRepository;

   private ProductMapper productMapper;

   @Autowired
   public ProductService(ProductRepository productRepository,
                         RestaurantRepository restaurantRepository, ProductMapper productMapper) {

      this.productRepository = productRepository;
      this.restaurantRepository = restaurantRepository;
      this.productMapper = productMapper;
   }

   public List<ProductDto> gatAll() {
      List<Product> products = new ArrayList<>(productRepository.findAll());

      return MapperUtil.convertlist(products, productMapper::showProductDetails);
   }

   public List<ProductDto> gatProductShort() {
      List<Product> products = new ArrayList<>(productRepository.findAll());

      return MapperUtil.convertlist(products, productMapper::convertToProductDto);
   }

   public ProductDto getProductById(long id) {
      Optional<Product> productOptional = productRepository.findById(id);
      ProductDto productDto = null;

      if (productOptional.isPresent()) {
         productDto = MapperUtil.convertlist(List.of(productOptional.get()), productMapper::showProductDetails).get(0);
      }

      return productDto;
   }

   public ProductDto addProduct(ProductDto productDto) throws ProductException {

//      Product product = productMapper.convertToProduct(productDto);
//      Product productResponse = productRepository.save(product);

//      Long isResponse = productResponse.getId();
//      if (isResponse > 0) {
//         return productMapper.convertToProductDto(productResponse);
//      }
//      else {
//         throw new ProductException("Product could not be added!");
//      }


      /**
       * old version
       */
      return productMapper.convertToProductDto(
              productRepository.save(productMapper.convertToProduct(productDto)));
   }

   public ProductDto updateProduct(ProductDto productDto) {
      return productMapper.convertToProductDto(
              productRepository.save(productMapper.convertToProduct(productDto)));
   }

   public ProductDto delete(Long id) throws ProductException {
      Product product = productRepository.findById(id).orElseThrow(() -> new ProductException(""));
      product.setAvailable(false);
      product = productRepository.save(product);
      return productMapper.convertToProductDto(product);
   }
}
