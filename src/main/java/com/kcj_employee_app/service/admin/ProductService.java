package com.kcj_employee_app.service.admin;

import com.kcj_employee_app.config.MapperUtil;
import com.kcj_employee_app.dto.ProductDto;
import com.kcj_employee_app.dto.RestaurantDto;
import com.kcj_employee_app.entity.Product;
import com.kcj_employee_app.entity.Restaurant;
import com.kcj_employee_app.exception.list.ProductException;
import com.kcj_employee_app.mapper.admin.ProductMapper;
import com.kcj_employee_app.repo.ProductRepository;
import com.kcj_employee_app.repo.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

   private final ProductRepository productRepository;

   private final RestaurantRepository restaurantRepository;

   private final ProductMapper productMapper;

   //   @Autowired
   public ProductService(ProductRepository productRepository,
                         RestaurantRepository restaurantRepository,
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
              .filter(Product::getAvailable)
              .sorted(Comparator.comparing(Product::getCreatedAt).reversed())
              .collect(Collectors.toList());

      return MapperUtil.convertlist(products, productMapper::showProductDetails);
   }

   // READ
   public List<ProductDto> getProductsShort() throws ProductException {
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
            return productDto;

         } else {
            throw new ProductException(
                    String.format("Product not found in database with id=%d",
                            id));
         }
      } else {
         throw new ProductException("There is no product ID to search for!");
      }
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
                       String.format("No restaurant found with Id=%d. I can't create a product!",
                               restaurantDto.getId()));
            }
         } else {
            throw new ProductException("The ID of the associated restaurant is missing. Product has not been created!");
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

   // UPDATE
   public ProductDto blockProduct(Long id) throws ProductException {

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
                       String.format("Failed to delete product in database with Id=%d!",
                               id));
            }
         } else {
            throw new ProductException(
                    String.format("Product not found in the database with Id=%d!",
                            id));
         }
      } else {
         throw new ProductException("The ID of the product to be deleted is missing!");
      }
   }

   // UPDATE
   public ProductDto unblockProduct(Long id) throws ProductException {

      if (id != null) {
         Optional<Product> productOptional = productRepository.findById(id);

         if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setAvailable(true); // isAvailable = false;

            Product productResponse = productRepository.save(product);

            if (productResponse != null) {
               return productMapper.convertToProductDto(productResponse);

            } else {
               throw new ProductException(
                       String.format("Failed to delete product in database with Id=%d!",
                               id));
            }
         } else {
            throw new ProductException(
                    String.format("Product not found in the database with Id=%d!",
                            id));
         }
      } else {
         throw new ProductException("The ID of the product to be deleted is missing!");
      }
   }

   // Aggregation
}
