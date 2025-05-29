package com.example.passfashion.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.passfashion.dto.Request.LoginRequest;
import com.example.passfashion.dto.Request.RegisterRequest;
import com.example.passfashion.dto.Response.UserResponse;
import com.example.passfashion.model.User;
import com.example.passfashion.repository.UserRepository;

@Service
public class UserService {
   @Autowired
    private UserRepository userRepository;

    public UserResponse login(@RequestBody LoginRequest request) {
        Optional<User> user = userRepository.findByEmailAndPwd(request.getEmail(), request.getPwd());
        if (user.isEmpty()) {
            throw new RuntimeException("Email hoặc mật khẩu không đúng");
        }
        return convertToUserResponse(user.get());
    }

    public UserResponse register(@RequestBody RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã tồn tại");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPwd(request.getPwd());
        user.setPhone(request.getPhone());

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
