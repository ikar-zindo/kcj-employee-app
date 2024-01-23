package com.kcurryjib.service.admin;

import com.kcurryjib.dto.RestaurantDto;
import com.kcurryjib.entity.Restaurant;
import com.kcurryjib.exception.list.RestaurantException;
import com.kcurryjib.mapper.admin.RestaurantMapper;
import com.kcurryjib.repo.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

   @Mock
   private RestaurantRepository restaurantRepositoryMock;

   @Mock
   private RestaurantMapper restaurantMapperMock;

   @InjectMocks
   private RestaurantService restaurantServiceTest;

   private Restaurant expectedRestaurant;

   private RestaurantDto expectedRestaurantDto;

   private RestaurantDto expectedRestaurantDtoWithoutId;

   @BeforeEach
   void init() {
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
      expectedRestaurantDto = RestaurantDto.builder()
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
      expectedRestaurantDtoWithoutId = RestaurantDto.builder()
              .name("Test name")
              .address("Test str., 000")
              .phoneNumber("+490000000")
              .openingHours("00:00-00:00")
              .cuisineType("Test cuisine type")
              .description("Test description")
              .socialMediaLinks("test-link.com")
              .isOpen(true)
              .build();
   }

   @Test
   void getRestaurantIdTest() throws RestaurantException {
      when(restaurantRepositoryMock.findById(anyLong()))
              .thenReturn(Optional.of(expectedRestaurant));
      when(restaurantMapperMock.convertToRestaurantDto(any(Restaurant.class)))
              .thenReturn(expectedRestaurantDto);

      RestaurantDto returnRestaurantDto = restaurantServiceTest.getById(1L);

      assertEquals(expectedRestaurantDto, returnRestaurantDto);
   }

//   @Test
//   @DisplayName("Testing adding a restaurant")
//   void addRestaurantTest() throws RestaurantException {
//      when(restaurantMapperMock.convertToRestaurant(any(RestaurantDto.class)))
//              .thenReturn(expectedRestaurant);
//      when(restaurantRepositoryMock.save(expectedRestaurant))
//              .thenReturn(expectedRestaurant);
//      when(restaurantMapperMock.convertToRestaurantDto(any(Restaurant.class)))
//              .thenReturn(expectedRestaurantDto);
//
//      RestaurantDto returnRestaurantDto = restaurantServiceTest.addRestaurant(expectedRestaurantDtoWithoutId);
//
//      assertEquals(expectedRestaurant, returnRestaurantDto);
//   }

   @Test
   @DisplayName("Testing updating restaurant information")
   void updateRestaurantTest() throws RestaurantException {
      when(restaurantRepositoryMock.findById(anyLong()))
              .thenReturn(Optional.of(expectedRestaurant));
      when(restaurantRepositoryMock.save(expectedRestaurant))
              .thenReturn(expectedRestaurant);
      when(restaurantMapperMock.convertToRestaurantDto(any(Restaurant.class)))
              .thenReturn(expectedRestaurantDto);

      RestaurantDto returnRestaurantDto = restaurantServiceTest.updateRestaurant(expectedRestaurantDto);

      assertEquals(expectedRestaurantDto, returnRestaurantDto);
   }

   @Test
   @DisplayName("Testing deleting restaurant information")
   void deleteRestaurantTest() throws RestaurantException {
      when(restaurantRepositoryMock.findById(anyLong()))
              .thenReturn(Optional.of(expectedRestaurant));
      when(restaurantRepositoryMock.save(expectedRestaurant))
              .thenReturn(expectedRestaurant);
      when(restaurantMapperMock.convertToRestaurantDto(any(Restaurant.class)))
              .thenReturn(expectedRestaurantDto);

      RestaurantDto returnRestaurantDto = restaurantServiceTest.closeRestaurant(1L);

      assertEquals(expectedRestaurantDto, returnRestaurantDto);
   }

   @Test
   void createRestaurantExceptionTest() {
      assertThrows(RestaurantException.class, () -> restaurantServiceTest.addRestaurant(expectedRestaurantDto));
   }

   @Test
   void getProductIdExceptionTest() {
      assertThrows(RestaurantException.class, () -> restaurantServiceTest.getById(null));
   }

   @Test
   void updateRestaurantExceptionTest() {
      expectedRestaurantDto.setId(null);
      assertThrows(RestaurantException.class, () -> restaurantServiceTest.updateRestaurant(expectedRestaurantDto));
   }

   @Test
   void deleteRestaurantExceptionTest() {
      assertThrows(RestaurantException.class, () -> restaurantServiceTest.closeRestaurant(null));
   }

   @Test
   void deleteRestaurantExceptionNoSaveTest() {
      when(restaurantRepositoryMock.findById(anyLong()))
              .thenReturn(Optional.of(expectedRestaurant));
      when(restaurantRepositoryMock.save(expectedRestaurant))
              .thenReturn(null);

      assertThrows(RestaurantException.class, () -> restaurantServiceTest.closeRestaurant(1L));
   }

   @Test
   void deleteRestaurantExceptionNoFindRestaurantTest() {
      when(restaurantRepositoryMock.findById(anyLong()))
              .thenReturn(Optional.empty());

      assertThrows(RestaurantException.class, () -> restaurantServiceTest.closeRestaurant(1L));
   }
}
