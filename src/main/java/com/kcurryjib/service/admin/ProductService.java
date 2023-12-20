package com.kcurryjib.service.admin;

import com.kcurryjib.config.MapperUtil;
import com.kcurryjib.dto.ProductDto;
import com.kcurryjib.dto.RestaurantDto;
import com.kcurryjib.entity.Product;
import com.kcurryjib.entity.Restaurant;
import com.kcurryjib.exceptions.ProductException;
import com.kcurryjib.mapper.admin.ProductMapper;
import com.kcurryjib.repo.ProductRepository;
import com.kcurryjib.repo.RestaurantRepository;
import jakarta.persistence.metamodel.ListAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
//   @Autowired
//   private ModelMapper modelMapper;

   private ProductRepository productRepository;

   private RestaurantRepository restaurantRepository;

   private ProductMapper productMapper;

   //   @Autowired
   public ProductService(ProductRepository productRepository,
                         RestaurantRepository restaurantRepository, ProductMapper productMapper) throws ProductException{

      this.productRepository = productRepository;
      this.restaurantRepository = restaurantRepository;
      this.productMapper = productMapper;
   }

   public List<ProductDto> getAll() throws ProductException {
      List<Product> products = new ArrayList<>(productRepository.findAll());

      return MapperUtil.convertlist(products, productMapper::showProductDetails);
   }

   public List<ProductDto> getAvailableProducts() {
      List<Product> products = new ArrayList<>(productRepository.findAll()).stream().filter(Product::isAvailable).collect(Collectors.toList());

      return MapperUtil.convertlist(products, productMapper::showProductDetails);
   }

   public List<ProductDto> getProductShort() {
      List<Product> products = new ArrayList<>(productRepository.findAll());

      return MapperUtil.convertlist(products, productMapper::convertToProductDto);
   }

   public ProductDto getProductById(Long id) {
      Optional<Product> productOptional = productRepository.findById(id);
      ProductDto productDto = null;

      if (productOptional.isPresent()) {
         productDto = MapperUtil.convertlist(List.of(productOptional.get()), productMapper::showProductDetails).get(0);
      }

      return productDto;
   }

   public ProductDto addProduct(ProductDto productDto) throws ProductException {

      if (productDto != null && productDto.getId() == null) {
         RestaurantDto restaurantDto = productDto.getRestaurantDto();

         if (restaurantDto != null && restaurantDto.getId() != null) {
            Restaurant restaurant = restaurantRepository.findById(restaurantDto.getId()).orElse(null);

            if (restaurant != null) {
               Product product = productMapper.convertToProduct(productDto);

               product.setRestaurant(restaurant);
//               product.setAvailable(true);
               product.setCreatedAt(LocalDateTime.now());

               Product productResponse = productRepository.save(product);
               Long idResponse = productResponse.getId();

               if (idResponse != null && idResponse > 0) {
                  return productMapper.convertToProductDto(productResponse);

               } else {
                  throw new ProductException("Could not create a client in the database");
               }
            } else {
               throw new ProductException(String.format("No restaurant found with Id=%d. I can't create a client!", restaurantDto.getId()));
            }
         } else {
            throw new ProductException("The ID of the associated restaurant is missing. I can't create a client!");
         }
      } else {
         throw new ProductException("Error processing received request body!");
      }
      /**
       * old version
       */
//      return productMapper.convertToProductDto(
//              productRepository.save(productMapper.convertToProduct(productDto)));
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
