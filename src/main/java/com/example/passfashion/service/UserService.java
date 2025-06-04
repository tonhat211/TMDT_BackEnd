package com.example.passfashion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.passfashion.dto.Request.LoginRequest;
import com.example.passfashion.dto.Request.RegisterRequest;
import com.example.passfashion.dto.Response.UserResponse;
import com.example.passfashion.model.User;
import com.example.passfashion.model.enums.Role;
import com.example.passfashion.repository.UserRepository;
import com.example.passfashion.security.JwtUtil;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse login(@RequestBody LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email không tồn tại"));

        if (!passwordEncoder.matches(request.getPwd(), user.getPwd())) {
            throw new RuntimeException("Mật khẩu không đúng");
        }
        UserResponse response = convertToUserResponse(user);
        response.setToken(jwtUtil.generateToken(user));
        return response;
    }

    public UserResponse register(@RequestBody RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã tồn tại");
        }
        // if (!request.getPwd().equals(request.getConfirmPwd())) {
        // throw new RuntimeException("Mật khẩu xác nhận không khớp");
        // }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPwd(passwordEncoder.encode(request.getPwd()));
        user.setPhone(request.getPhone());
        user.setRole(Role.USER);

        User saved = userRepository.save(user);
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
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setBirthday(user.getBirthday());
        response.setPhone(user.getPhone());
        response.setImageUrl(user.getImage() != null ? user.getImage().getUrl() : null);
        return response;
    }
}
