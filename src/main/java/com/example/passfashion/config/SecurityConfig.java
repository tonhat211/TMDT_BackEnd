package com.example.passfashion.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.passfashion.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults()) // Cho phép CORS
                .csrf(csrf -> csrf.disable()) // Vô hiệu hóa CSRF
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/products/create").authenticated()
                        .requestMatchers("/api/v1/products/material").authenticated()
                        .requestMatchers("/api/v1/products/detail/**").permitAll()
                        .requestMatchers("/api/v1/products/**").permitAll()
                        .requestMatchers("/api/v1/upload").authenticated() // Yêu cầu xác thực
                        .requestMatchers("/api/v1/comments/**").permitAll()
                        .requestMatchers("/api/v1/categories/**").permitAll()
                        .requestMatchers("/api/v1/wishlists/**").permitAll()
                        .requestMatchers("/api/v1/credit_card/**").permitAll()
                        .requestMatchers("/api/v1/users/**").permitAll()
                        .requestMatchers("/api/v1/orders/**").permitAll()
                        .requestMatchers("/api/v1/vouchers/**").permitAll()
                        .requestMatchers("/uploads/**").permitAll() // Cho phép truy cập file đã upload
                        .requestMatchers("/images/**", "/css/**", "/js/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
