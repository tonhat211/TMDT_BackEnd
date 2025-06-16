package com.example.passfashion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.passfashion.dto.Request.LoginRequest;
import com.example.passfashion.dto.Request.RegisterRequest;
import com.example.passfashion.dto.Response.UserResponse;
import com.example.passfashion.model.User;
import com.example.passfashion.model.enums.Role;
import com.example.passfashion.repository.UserRepository;
import com.example.passfashion.security.JwtUtil;

import jakarta.validation.Valid;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse login(@Valid LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email không tồn tại"));

        if (passwordEncoder.matches(request.getPwd(), user.getPwd())) {
            // sysout pass coder
            throw new RuntimeException("Mật khẩu không đúng");
        }
        UserResponse response = convertToUserResponse(user);
        response.setToken(jwtUtil.generateToken(user));
        return response;
    }

    public UserResponse register(@Valid RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã tồn tại");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPwd(passwordEncoder.encode(request.getPwd()));
        user.setPhone(request.getPhone());
        user.setRole(Role.USER);

        User saved = userRepository.save(user);
        UserResponse response = convertToUserResponse(saved);
        response.setToken(jwtUtil.generateToken(saved)); // Trả về token
        return convertToUserResponse(saved);
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Không tìm thấy người dùng với id: " + id));
        return convertToUserResponse(user);
    }

    // 👉 Đây là method chuyển đổi User → UserResponse, viết nội bộ trong service
    private UserResponse convertToUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setBirthday(user.getBirthday());
        response.setPhone(user.getPhone());
        response.setImageUrl(user.getImage() != null ? user.getImage().getUrl() : null);
        return response;
    }

    public User getCurrentUser() {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId == null) {
            throw new IllegalStateException("Người dùng chưa được xác thực");
        }
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với id: " + userId));
    }
}
