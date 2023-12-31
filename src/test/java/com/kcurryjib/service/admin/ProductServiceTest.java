package com.kcurryjib.service.admin;

import com.kcurryjib.dto.ProductDto;
import com.kcurryjib.dto.RestaurantDto;
import com.kcurryjib.entity.Product;
import com.kcurryjib.entity.Restaurant;
import com.kcurryjib.exception.list.ProductException;
import com.kcurryjib.mapper.admin.ProductMapper;
import com.kcurryjib.repo.ProductRepository;
import com.kcurryjib.repo.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

   @Mock
   private ProductRepository productRepositoryMock;

   @Mock
   private RestaurantRepository restaurantRepositoryMock;

   @Mock
   private ProductMapper productMapperMock;

   @Mock
   private ModelMapper mapper;

   @InjectMocks
   private ProductService productServiceTest;

   private Product expectedProduct;

   private ProductDto expectedProductDto;

   private Restaurant expectedRestaurant;

   private RestaurantDto expectedRestaurantDto;

   @BeforeEach
   void init() {
      expectedProduct = Product.builder()
              .id(1L)
              .name("TestName")
              .description("TestDescription")
              .price(new BigDecimal(1))
              .imageUrl("1.jpg")
              .createdAt(LocalDateTime.now())
              .isAvailable(true)
              .build();

      expectedProductDto = ProductDto.builder()
              .id(1L)
              .name("TestName")
              .description("TestDescription")
              .price(new BigDecimal(1))
              .imageUrl("1.jpg")
              .createdAt(LocalDateTime.now())
              .isAvailable(true)
              .build();

      expectedRestaurant = Restaurant.builder()
              .id(1L)
              .name("TestName")
              .address("TestAddress")
              .phoneNumber("+490000000")
              .openingHours("00:00-00:00")
              .cuisineType("TestCuisineType")
              .description("TestDescription")
              .socialMediaLinks("test-link.com")
              .isOpen(true)
              .build();

      expectedRestaurantDto = RestaurantDto.builder()
              .id(expectedRestaurant.getId())
              .name(expectedRestaurant.getName())
              .address(expectedRestaurant.getAddress())
              .phoneNumber(expectedRestaurant.getPhoneNumber())
              .openingHours(expectedRestaurant.getOpeningHours())
              .cuisineType(expectedRestaurant.getCuisineType())
              .description(expectedRestaurant.getDescription())
              .socialMediaLinks(expectedRestaurant.getSocialMediaLinks())
              .isOpen(expectedRestaurant.isOpen())
              .build();
   }

   @Test
   void getProductIdTest() throws ProductException {
      when(productRepositoryMock.findById(anyLong())).thenReturn(Optional.of(expectedProduct));
      when(productMapperMock.showProductDetails(any(Product.class))).thenReturn(expectedProductDto);


      ProductDto returnProductDto  = productServiceTest.getProductById(1L);

      assertEquals(expectedProductDto, returnProductDto);
   }

   @Test
   void addProductTest() throws ProductException {
      when(restaurantRepositoryMock.findById(anyLong())).thenReturn(Optional.of(expectedRestaurant));

      when(productMapperMock.convertToProduct(any(ProductDto.class))).thenReturn(expectedProduct);
      when(productRepositoryMock.save(expectedProduct)).thenReturn(expectedProduct);
      when(productMapperMock.convertToProductDto(any(Product.class))).thenReturn(expectedProductDto);

      ProductDto expectedProductWithoutId = ProductDto.builder()
              .name(expectedProduct.getName())
              .description(expectedProduct.getDescription())
              .price(expectedProduct.getPrice())
              .imageUrl(expectedProduct.getImageUrl())
              .createdAt(expectedProduct.getCreatedAt())
              .isAvailable(expectedProduct.isAvailable())
              .restaurantDto(expectedRestaurantDto)
              .build();

      ProductDto returnProductDto = productServiceTest.addProduct(expectedProductWithoutId);

      assertEquals(expectedProductDto, returnProductDto);
   }

   @Test
   @DisplayName("Testing updating product information")
   void updateProductTest() throws ProductException {
      when(productRepositoryMock.findById(anyLong())).thenReturn(Optional.of(expectedProduct));
      when(productRepositoryMock.save(expectedProduct)).thenReturn(expectedProduct);
      when(productMapperMock.convertToProductDto(any(Product.class))).thenReturn(expectedProductDto);

      ProductDto expectedProductWithRestaurant = ProductDto.builder()
              .id(expectedProduct.getId())
              .name(expectedProduct.getName())
              .description(expectedProduct.getDescription())
              .price(expectedProduct.getPrice())
              .imageUrl(expectedProduct.getImageUrl())
              .createdAt(expectedProduct.getCreatedAt())
              .isAvailable(expectedProduct.isAvailable())
              .restaurantDto(expectedRestaurantDto)
              .build();


      ProductDto returnProductDto = productServiceTest.updateProduct(expectedProductWithRestaurant);

      assertEquals(expectedProductDto, returnProductDto);
   }

   @Test
   void deleteProductTest() throws ProductException {
      when(productRepositoryMock.findById(anyLong())).thenReturn(Optional.of(expectedProduct));
      when(productRepositoryMock.save(expectedProduct)).thenReturn(expectedProduct);
      when(productMapperMock.convertToProductDto(any(Product.class))).thenReturn(expectedProductDto);

      ProductDto returnProductDto = productServiceTest.deleteProduct(1L);

      assertEquals(expectedProductDto, returnProductDto);
   }

   @Test
   void createClientExceptionTest() {
      assertThrows(ProductException.class, () -> productServiceTest.addProduct(expectedProductDto));
   }

   @Test
   void getClientIdExceptionTest() {
      assertThrows(ProductException.class, () -> productServiceTest.getProductById(null));
   }

   @Test
   void updateClientExceptionTest() {
      expectedProductDto.setId(null);
      assertThrows(ProductException.class, () -> productServiceTest.updateProduct(expectedProductDto));
   }

   @Test
   void deleteClientExceptionTest() {
      assertThrows(ProductException.class, () -> productServiceTest.deleteProduct(null));
   }

   @Test
   void deleteClientExceptionNoSaveTest() {
      when(productRepositoryMock.findById(anyLong())).thenReturn(Optional.of(expectedProduct));
      when(productRepositoryMock.save(expectedProduct)).thenReturn(null);
      assertThrows(ProductException.class, () -> productServiceTest.deleteProduct(1L));
   }
   @Test
   void deleteClientExceptionNoFindClientTest() {
      when(productRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());
      assertThrows(ProductException.class, () -> productServiceTest.deleteProduct(1L));
   }
}
