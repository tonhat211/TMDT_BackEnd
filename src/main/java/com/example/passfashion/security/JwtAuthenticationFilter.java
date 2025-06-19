package com.example.passfashion.security;

import java.beans.SimpleBeanInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.passfashion.model.User;
import com.example.passfashion.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                Long userId = jwtUtil.getUserIdFromToken(token); // Lấy userId
                String role = jwtUtil.getClaimFromToken(token, claims -> claims.get("role", String.class));

                if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    Optional<User> userOpt = userRepository.findById(userId); // Tìm user theo ID
                    System.out.println("userOpt.isPresent()" + userOpt.isPresent());
                    System.out.println(
                            "jwtUtil.isTokenValid(token, userOpt.get())" + jwtUtil.isTokenValid(token, userOpt.get()));
                    if (userOpt.isPresent() && jwtUtil.isTokenValid(token, userOpt.get())) {
                        List<SimpleGrantedAuthority> authorities = Collections.singletonList(
                                new SimpleGrantedAuthority("ROLE_" + role));
                        System.out.println("authorities" + authorities);
                        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                                userId, null, authorities); // Principal là userId
                        auth.setDetails(authHeader); // Lưu authHeader vào details
                        SecurityContextHolder.getContext().setAuthentication(auth);
                        logger.info("success userId: {}", userId);
                    } else {
                        logger.warn("User không tồn tại hoặc token không hợp lệ: {}", userId);
                    }
                }
            } catch (Exception e) {
                logger.error("Lỗi khi xử lý token: {}", e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }
}