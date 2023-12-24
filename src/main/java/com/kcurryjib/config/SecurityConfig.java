//package com.kcurryjib.config;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfig {
//   private final UserDetailsService userDetailsService;
//
//   @Autowired
//   public SecurityConfig(UserDetailsService userDetailsService) {
//      this.userDetailsService = userDetailsService;
//   }
//
//   @Bean
//   public PasswordEncoder passwordEncoder(){
//      return new BCryptPasswordEncoder();
//   }
//
//   @Bean
//   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//      http
//              .logout((logout) -> logout.logoutUrl("/logout").permitAll()
//                      .logoutSuccessUrl("/"))
//              .authorizeHttpRequests((requests) -> requests
//                      .requestMatchers("/",
//                              "/style.css",
//                              "/img/**",
//                              "/error",
//                              "/products",
//                              "/restaurants",
//                              "/swagger-ui/**",
//                              "/menu")
//                      .permitAll()
//                      .anyRequest()
//                      .authenticated()
//              )
//              .formLogin((form) -> form
//                      .loginPage("/login")
//                      .permitAll()
//              );
//
//      return http.build();
//   }
//}