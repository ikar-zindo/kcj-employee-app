package com.kcurryjib.service.admin;

import com.kcurryjib.dto.ProductDto;
import com.kcurryjib.entity.Product;
import com.kcurryjib.entity.Restaurant;
import com.kcurryjib.exception.exceptionsList.ProductException;
import com.kcurryjib.mapper.admin.ProductMapper;
import com.kcurryjib.repo.ProductRepository;
import com.kcurryjib.repo.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
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
              .phoneNumber("+49123456789")
              .openingHours("00:00-00:00")
              .cuisineType("TestCuisineType")
              .description("TestDescription")
              .socialMediaLinks("test-link.com")
              .isOpen(true)
              .build();
   }

   @Test
   void getProductIdTest() throws ProductException {
      when(productRepositoryMock.findById(anyLong())).thenReturn(Optional.of(expectedProduct));
      when(productMapperMock.convertToProductDto(any(Product.class))).thenReturn(expectedProductDto);


      ProductDto returnProductDto  = productServiceTest.getProductById(1L);
      assertEquals(expectedProductDto, returnProductDto);
//      assertEquals(expectedProductDto.getId(), returnProductDto.getId());
   }
}
