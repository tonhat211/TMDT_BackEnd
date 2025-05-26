package com.example.passfashion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .cors(Customizer.withDefaults()) // kích hoạt CORS
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/api/v1/auth/**").permitAll() // cho phep truy cap
                                                                                                // khong can xac thuc,
                                                                                                // dang nhap
                                                .requestMatchers("/api/v1/products/**").permitAll()
                                                .requestMatchers("/api/v1/categories/**").permitAll()
                                                .requestMatchers("/api/v1/wishlists/**").permitAll() // ✅ Cho phép truy
                                                                                                     // cập Wishlist API
                                                .requestMatchers(
                                                                "/images/**", // cho phép truy cập ảnh
                                                                "/css/**",
                                                                "/js/**")
                                                .permitAll()


                                                .anyRequest().authenticated())
                                .csrf(csrf -> csrf.disable()); // disable CSRF

                return http.build();
        }
}
