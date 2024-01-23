package com.kcurryjib.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

   @Bean
   public PasswordEncoder bCryptPasswordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      return http
              .csrf(AbstractHttpConfigurer::disable)
//              .sessionManagement(session -> session
//                      .sessionCreationPolicy(STATELESS))
              .logout(
                      logout -> logout
                              .logoutUrl("/logout")
                              .permitAll()
//                              .logoutSuccessUrl("/")
              )
              .authorizeHttpRequests(
                      requests -> requests
                              .requestMatchers(
//                                      "/**",
//                                      "/admin/**",
                                      "/",
                                      "/rest/employees/**",
                                      "/rest/orders/**",
                                      "/menu/**",
                                      "/auth/login",
                                      "/auth/token",
                                      "/swagger-ui.html",
                                      "/api/v1/auth/**",
                                      "/v3/api-docs/**",
                                      "/swagger-ui/**",
                                      "/images/**",
                                      "/assets/**"
                              )
                              .permitAll()
                              .anyRequest()
                              .authenticated()
              )
              .formLogin(
                      login -> login
                              .loginPage("/login")
                              .defaultSuccessUrl("/") // URL перенаправления по умолчанию
                              .successHandler((request, response, authentication) -> {
                                 Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

                                 if (authorities.stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
                                    response.sendRedirect("/admin/products");

                                 } else if (authorities.stream().anyMatch(r -> r.getAuthority().equals("ROLE_MANAGER"))) {
                                    response.sendRedirect("/admin/employees");

                                 } else if (authorities.stream().anyMatch(r -> r.getAuthority().equals("ROLE_USER"))) {
                                    response.sendRedirect("/employee/orders");

                              } else {
                                    response.sendRedirect("/");
                                 }
                              })
                              .permitAll()
              )
              .build();
   }
}
