package com.kcurryjib.service.admin;

import com.kcurryjib.config.MapperUtil;
import com.kcurryjib.dto.ProductDto;
import com.kcurryjib.dto.RestaurantDto;
import com.kcurryjib.entity.Product;
import com.kcurryjib.entity.Restaurant;
import com.kcurryjib.exception.list.ProductException;
import com.kcurryjib.mapper.admin.ProductMapper;
import com.kcurryjib.repo.ProductRepository;
import com.kcurryjib.repo.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
   public ProductService(ProductRepository productRepository, RestaurantRepository restaurantRepository,
                         ProductMapper productMapper) throws ProductException {

      this.productRepository = productRepository;
      this.restaurantRepository = restaurantRepository;
      this.productMapper = productMapper;
   }

   // READ
   public List<ProductDto> getAll() throws ProductException {
      List<Product> products = new ArrayList<>(productRepository.findAll());

      return MapperUtil.convertlist(products, productMapper::showProductDetails);
   }

   // READ
   public List<ProductDto> getAvailableProducts() throws ProductException {
      List<Product> products = new ArrayList<>(productRepository.findAll()).stream()
              .filter(Product::isAvailable)
              .collect(Collectors.toList());

      return MapperUtil.convertlist(products, productMapper::showProductDetails);
   }

   // READ
   public List<ProductDto> getProductShort() throws ProductException {
      List<Product> products = new ArrayList<>(productRepository.findAll());

      return MapperUtil.convertlist(products, productMapper::convertToProductDto);
   }

   // READ
   public ProductDto getProductById(Long id) throws ProductException {
      ProductDto productDto = null;

      if (id != null) {
         Optional<Product> productOptional = productRepository.findById(id);

         if (productOptional.isPresent()) {
            productDto = productMapper.showProductDetails(productOptional.get());
//                    MapperUtil.convertlist(
//                    List.of(productOptional.get()), productMapper::showProductDetails).get(0);
         } else {
            throw new ProductException(
                    String.format("Product not found in database with id=%d", id));
         }
      } else {
         throw new ProductException("There is no product ID to search for!");
      }

      return productDto;
   }

   // CREATE
   public ProductDto addProduct(ProductDto productDto) throws ProductException {

      if (productDto != null && productDto.getId() == null) {
         RestaurantDto restaurantDto = productDto.getRestaurantDto();

         if (restaurantDto != null && restaurantDto.getId() != null) {
            Restaurant restaurant = restaurantRepository.findById(restaurantDto.getId()).orElse(null);

            if (restaurant != null) {
               Product product = productMapper.convertToProduct(productDto);

               product.setRestaurant(restaurant);
               product.setCreatedAt(LocalDateTime.now());

               Product productResponse = productRepository.save(product);
               Long idResponse = productResponse.getId();

               if (idResponse != null && idResponse > 0) {
                  return productMapper.convertToProductDto(productResponse);

               } else {
                  throw new ProductException("Could not create a product in the database");
               }
            } else {
               throw new ProductException(
                       String.format("No restaurant found with Id=%d. I can't create a product!", restaurantDto.getId()));
            }
         } else {
            throw new ProductException("The ID of the associated restaurant is missing. I can't create a product!");
         }
      } else {
         throw new ProductException("Error processing received request body!");
      }
   }

   // UPDATE
   public ProductDto updateProduct(ProductDto productDto) throws ProductException {

      if (productDto.getName() == null) {
         productDto.setName("new product");
      }

      if (productDto.getId() != null) {
         Optional<Product> productOptional = productRepository.findById(productDto.getId());
         RestaurantDto restaurantDto = productDto.getRestaurantDto();
         Restaurant restaurant = restaurantRepository.findById(restaurantDto.getId()).orElse(null);

         if (productOptional.isPresent()) {
            Product product = productOptional.get();

            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setImageUrl(productDto.getImageUrl());
            product.setAvailable(productDto.isAvailable());
            product.setRestaurant(restaurant);

            Product productResponse = productRepository.save(product);

            if (productResponse != null) {
               return productMapper.convertToProductDto(productResponse);

            } else {
               throw new ProductException(
                       String.format("Failed to update the product in the database with Id=%d!",
                               productDto.getId()));
            }
         } else {
            throw new ProductException(
                    String.format("The product was not found in the database with Id=%d!",
                            productDto.getId()));
         }
      } else {
         throw new ProductException("Error processing received body request!");
      }
   }

   //DELETE
   public ProductDto deleteProduct(Long id) throws ProductException {

      if (id != null) {
         Optional<Product> productOptional = productRepository.findById(id);

         if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setAvailable(false); // isAvailable = false;

            Product productResponse = productRepository.save(product);

            if (productResponse != null) {
               return productMapper.convertToProductDto(productResponse);

            } else {
               throw new ProductException(
                       String.format("Failed to delete product in database with Id=%d!", id));
            }
         } else {
            throw new ProductException(
                    String.format("Product not found in the database with Id=%d!", id));
         }
      } else {
         throw new ProductException("The ID of the product to be deleted is missing!");
      }
   }

   // Aggregation
}
