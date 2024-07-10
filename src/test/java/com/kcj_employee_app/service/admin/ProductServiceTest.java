package com.kcj_employee_app.service.admin;

import com.kcj_employee_app.dto.ProductDto;
import com.kcj_employee_app.dto.RestaurantDto;
import com.kcj_employee_app.entity.Product;
import com.kcj_employee_app.entity.Restaurant;
import com.kcj_employee_app.exception.list.ProductException;
import com.kcj_employee_app.mapper.admin.ProductMapper;
import com.kcj_employee_app.repo.ProductRepository;
import com.kcj_employee_app.repo.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

   @Mock
   private ProductRepository productRepositoryMock;

   @Mock
   private RestaurantRepository restaurantRepositoryMock;

   @Mock
   private ProductMapper productMapperMock;

   @InjectMocks
   private ProductService productServiceTest;

   private Product expectedProduct;

   private ProductDto expectedProductDto;

   private Restaurant expectedRestaurant;

   private ProductDto expectedProductDtoWithoutId;

   private ProductDto expectedProductDtoWithRestaurant;

   @BeforeEach
   void init() {
      // test instance of the product
      expectedProduct = Product.builder()
              .id(1L)
              .name("Test name")
              .description("Test description")
              .price(new BigDecimal(1))
              .imageUrl("1.jpg")
              .createdAt(LocalDateTime.now())
              .isAvailable(true)
              .build();

      // test instance of the productDto
      expectedProductDto = ProductDto.builder()
              .id(1L)
              .name("Test name")
              .description("Test description")
              .price(new BigDecimal(1))
              .imageUrl("1.jpg")
              .createdAt(LocalDateTime.now())
              .isAvailable(true)
              .build();

      // test instance of the restaurant
      expectedRestaurant = Restaurant.builder()
              .id(1L)
              .name("Test name")
              .address("Test str., 000")
              .phoneNumber("+490000000")
              .openingHours("00:00-00:00")
              .cuisineType("Test cuisine type")
              .description("Test description")
              .socialMediaLinks("test-link.com")
              .isOpen(true)
              .build();

      // test instance of the restaurantDto
      RestaurantDto expectedRestaurantDto = RestaurantDto.builder()
              .id(1L)
              .name("Test name")
              .address("Test str., 000")
              .phoneNumber("+490000000")
              .openingHours("00:00-00:00")
              .cuisineType("Test cuisine type")
              .description("Test description")
              .socialMediaLinks("test-link.com")
              .isOpen(true)
              .build();

      // test instance of the product without id
      expectedProductDtoWithoutId = ProductDto.builder()
              .name(expectedProduct.getName())
              .description(expectedProduct.getDescription())
              .price(expectedProduct.getPrice())
              .imageUrl(expectedProduct.getImageUrl())
              .createdAt(expectedProduct.getCreatedAt())
              .isAvailable(expectedProduct.getAvailable())
              .restaurantDto(expectedRestaurantDto)
              .build();

      // test instance of the product with restaurant
      expectedProductDtoWithRestaurant = ProductDto.builder()
              .id(expectedProduct.getId())
              .name(expectedProduct.getName())
              .description(expectedProduct.getDescription())
              .price(expectedProduct.getPrice())
              .imageUrl(expectedProduct.getImageUrl())
              .createdAt(expectedProduct.getCreatedAt())
              .isAvailable(expectedProduct.getAvailable())
              .restaurantDto(expectedRestaurantDto)
              .build();
   }

   @Test
   void getProductIdTest() throws ProductException {
      when(productRepositoryMock.findById(anyLong()))
              .thenReturn(Optional.of(expectedProduct));
      when(productMapperMock.showProductDetails(any(Product.class)))
              .thenReturn(expectedProductDto);

      ProductDto returnProductDto  = productServiceTest.getProductById(1L);

      assertEquals(expectedProductDto, returnProductDto);
   }

   @Test
   void addProductTest() throws ProductException {
      when(restaurantRepositoryMock.findById(anyLong()))
              .thenReturn(Optional.of(expectedRestaurant));
      when(productMapperMock.convertToProduct(any(ProductDto.class)))
              .thenReturn(expectedProduct);
      when(productRepositoryMock.save(expectedProduct))
              .thenReturn(expectedProduct);
      when(productMapperMock.convertToProductDto(any(Product.class)))
              .thenReturn(expectedProductDto);

      ProductDto returnProductDto = productServiceTest.addProduct(expectedProductDtoWithoutId);

      assertEquals(expectedProductDto, returnProductDto);
   }

   @Test
   @DisplayName("Testing updating product information")
   void updateProductTest() throws ProductException {
      when(productRepositoryMock.findById(anyLong()))
              .thenReturn(Optional.of(expectedProduct));
      when(productRepositoryMock.save(expectedProduct))
              .thenReturn(expectedProduct);
      when(productMapperMock.convertToProductDto(any(Product.class)))
              .thenReturn(expectedProductDto);

      ProductDto returnProductDto = productServiceTest.updateProduct(expectedProductDtoWithRestaurant);

      assertEquals(expectedProductDto, returnProductDto);
   }

   @Test
   void deleteProductTest() throws ProductException {
      when(productRepositoryMock.findById(anyLong()))
              .thenReturn(Optional.of(expectedProduct));
      when(productRepositoryMock.save(expectedProduct))
              .thenReturn(expectedProduct);
      when(productMapperMock.convertToProductDto(any(Product.class)))
              .thenReturn(expectedProductDto);

      ProductDto returnProductDto = productServiceTest.blockProduct(1L);

      assertEquals(expectedProductDto, returnProductDto);
   }

   @Test
   void createProductExceptionTest() {
      assertThrows(ProductException.class, () -> productServiceTest.addProduct(expectedProductDto));
   }

   @Test
   void getProductIdExceptionTest() {
      assertThrows(ProductException.class, () -> productServiceTest.getProductById(null));
   }

   @Test
   void updateProductExceptionTest() {
      expectedProductDto.setId(null);
      assertThrows(ProductException.class, () -> productServiceTest.updateProduct(expectedProductDto));
   }

   @Test
   void deleteProductExceptionTest() {
      assertThrows(ProductException.class, () -> productServiceTest.blockProduct(null));
   }

   @Test
   void deleteProductExceptionNoSaveTest() {
      when(productRepositoryMock.findById(anyLong()))
              .thenReturn(Optional.of(expectedProduct));
      when(productRepositoryMock.save(expectedProduct))
              .thenReturn(null);

      assertThrows(ProductException.class, () -> productServiceTest.blockProduct(1L));
   }

   @Test
   void deleteProductExceptionNoFindProductTest() {
      when(productRepositoryMock.findById(anyLong()))
              .thenReturn(Optional.empty());

      assertThrows(ProductException.class, () -> productServiceTest.blockProduct(1L));
   }
}
