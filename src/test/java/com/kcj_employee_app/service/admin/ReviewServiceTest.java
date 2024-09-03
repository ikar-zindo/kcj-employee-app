package com.kcj_employee_app.service.admin;

import com.kcj_employee_app.dto.CustomerDto;
import com.kcj_employee_app.dto.RestaurantDto;
import com.kcj_employee_app.dto.ReviewDto;
import com.kcj_employee_app.entity.Customer;
import com.kcj_employee_app.entity.Restaurant;
import com.kcj_employee_app.entity.Review;
import com.kcj_employee_app.exception.list.ReviewException;
import com.kcj_employee_app.mapper.admin.ReviewMapper;
import com.kcj_employee_app.repo.CustomerRepository;
import com.kcj_employee_app.repo.RestaurantRepository;
import com.kcj_employee_app.repo.ReviewRepository;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

   @Mock
   private ReviewMapper reviewMapperMock;

   @Mock
   private ReviewRepository reviewRepositoryMock;

   @Mock
   private RestaurantRepository restaurantRepositoryMock;

   @Mock
   private CustomerRepository customerRepositoryMock;

   @InjectMocks
   private ReviewService reviewServiceTest;

   private Review expectedReview;

   private Review expectedReviewInfo;

   private ReviewDto expectedReviewDto;

   private ReviewDto expectedReviewWithoutId;

   private ReviewDto expectedReviewDtoInfo;

   private Restaurant expectedRestaurant;

   private Customer expectedCustomer;

   @BeforeEach
   void init() {
      LocalDateTime localDateTime = LocalDateTime.now();

      // test instance of the restaurant
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

      // test instance of the restaurantDto
      RestaurantDto expectedRestaurantDto = RestaurantDto.builder()
              .id(expectedRestaurant.getId())
              .name(expectedRestaurant.getName())
              .address(expectedRestaurant.getAddress())
              .phoneNumber(expectedRestaurant.getPhoneNumber())
              .openingHours(expectedRestaurant.getOpeningHours())
              .cuisineType(expectedRestaurant.getCuisineType())
              .description(expectedRestaurant.getDescription())
              .socialMediaLinks(expectedRestaurant.getSocialMediaLinks())
              .isOpen(expectedRestaurant.getOpen())
              .build();

      // test instance of the customer
      expectedCustomer = Customer.builder()
              .id(UUID.fromString("d234d99d-170e-42f7-b6ae-435ee56f49a1"))
              .firstName("First")
              .lastName("Second")
              .email("test@mail.com")
              .password("??????????")
              .phoneNumber("+499999999")
              .address("Test str., 000")
              .postalCode("00000")
              .createdAt(localDateTime)
              .isBlocked(true)
              .build();

      // test instance of the customerDto
      CustomerDto expectedCustomerDto = CustomerDto.builder()
              .id(expectedCustomer.getId())
              .firstName(expectedCustomer.getFirstName())
              .lastName(expectedCustomer.getLastName())
              .email(expectedCustomer.getEmail())
              .password(expectedCustomer.getPassword())
              .phoneNumber(expectedCustomer.getPhoneNumber())
              .address(expectedCustomer.getAddress())
              .postalCode(expectedCustomer.getPostalCode())
              .createdAt(expectedCustomer.getCreatedAt())
              .isBlocked(expectedCustomer.getBlocked())
              .build();

      // test instance of the review
      expectedReview = Review.builder()
              .id(1L)
              .rating(new BigDecimal(5))
              .comment("Test comment")
              .createdAt(localDateTime)
              .build();

      // test instance of the review without id
      expectedReviewWithoutId = ReviewDto.builder()
              .rating(new BigDecimal(5))
              .comment("Test comment")
              .createdAt(localDateTime)
              .restaurantDto(expectedRestaurantDto)
              .customerDto(expectedCustomerDto)
              .build();

      // test instance of the reviewDto
      expectedReviewDto = ReviewDto.builder()
              .id(expectedReview.getId())
              .rating(expectedReview.getRating())
              .comment(expectedReview.getComment())
              .createdAt(expectedReview.getCreatedAt())
              .build();

      // test instance of the review with info about restaurant & customer
      expectedReviewInfo = Review.builder()
              .id(1L)
              .rating(new BigDecimal(5))
              .comment("Test comment")
              .createdAt(localDateTime)
              .customer(expectedCustomer)
              .restaurant(expectedRestaurant)
              .build();

      // test instance of the reviewDto with info about restaurant & customer
      expectedReviewDtoInfo = ReviewDto.builder()
              .id(expectedReviewInfo.getId())
              .rating(expectedReviewInfo.getRating())
              .comment(expectedReviewInfo.getComment())
              .createdAt(expectedReviewInfo.getCreatedAt())
              .customerDto(expectedCustomerDto)
              .restaurantDto(expectedRestaurantDto)
              .build();
   }

   @Test
   void getByIdTest() throws ReviewException {
      when(reviewRepositoryMock.findById(anyLong()))
              .thenReturn(Optional.of(expectedReview));
      when(reviewMapperMock.showReviewDtoWithCustomer(any(Review.class)))
              .thenReturn(expectedReviewDto);

      ReviewDto returnReviewDto = reviewServiceTest.getById(1L);

      assertEquals(expectedReviewDto, returnReviewDto);
   }

   @Test
   void addReviewTest() throws ReviewException {
      when(restaurantRepositoryMock.findById(anyLong()))
              .thenReturn(Optional.of(expectedRestaurant));
      when(customerRepositoryMock.findById(any(UUID.class)))
              .thenReturn(Optional.of(expectedCustomer));

      when(reviewMapperMock.convertToReview(any(ReviewDto.class)))
              .thenReturn(expectedReview);
      when(reviewRepositoryMock.save(expectedReview))
              .thenReturn(expectedReview);
      when(reviewMapperMock.convertToReviewDto(any(Review.class)))
              .thenReturn(expectedReviewDto);

      ReviewDto returnReviewDto = reviewServiceTest.addReview(expectedReviewWithoutId);

      assertEquals(expectedReviewDto, returnReviewDto);
   }

   @Test
   @DisplayName("Testing updating review information")
   void updateReviewTest() throws ReviewException {
      when(reviewRepositoryMock.findById(anyLong()))
              .thenReturn(Optional.of(expectedReviewInfo));
      when(reviewRepositoryMock.save(expectedReviewInfo))
              .thenReturn(expectedReviewInfo);
      when(reviewMapperMock.convertToReviewDto(any(Review.class)))
              .thenReturn(expectedReviewDtoInfo);

      ReviewDto returnProductDto = reviewServiceTest.updateReview(expectedReviewDtoInfo);
      ReviewDto convertedReviewDto = reviewMapperMock.convertToReviewDto(expectedReviewInfo);

      assertEquals(convertedReviewDto, returnProductDto);
   }

   @Test
   void deleteProductTest() throws ReviewException {
      when(reviewRepositoryMock.findById(anyLong()))
              .thenReturn(Optional.of(expectedReview));
      when(reviewMapperMock.convertToReviewDto(any(Review.class)))
              .thenReturn(expectedReviewDto);

      ReviewDto returnReviewDto = reviewServiceTest.deleteReview(1L);

      assertEquals(expectedReviewDto, returnReviewDto);
   }

   @Test
   void createReviewExceptionTest() {
      assertThrows(ReviewException.class, () -> reviewServiceTest.addReview(expectedReviewDto));
   }

   @Test
   void getReviewIdExceptionTest() {
      assertThrows(ReviewException.class, () -> reviewServiceTest.getById(null));
   }

   @Test
   void updateReviewExceptionTest() {
      expectedReviewDtoInfo.setId(null);
      assertThrows(ReviewException.class, () -> reviewServiceTest.updateReview(expectedReviewDtoInfo));
   }

   @Test
   void deleteReviewExceptionTest() {
      assertThrows(ReviewException.class, () -> reviewServiceTest.deleteReview(null));
   }

   @Test
   void deleteReviewExceptionNoFindReviewTest() {
      when(reviewRepositoryMock.findById(anyLong()))
              .thenReturn(Optional.empty());

      assertThrows(ReviewException.class, () -> reviewServiceTest.deleteReview(1L));
   }
}
