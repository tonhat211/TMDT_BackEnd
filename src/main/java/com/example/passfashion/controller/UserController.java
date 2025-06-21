
package com.example.passfashion.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.passfashion.model.Order;
import com.example.passfashion.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.passfashion.dto.Request.AddressUpdateRequest;
import com.example.passfashion.dto.Request.LoginRequest;
import com.example.passfashion.dto.Request.RegisterRequest;
import com.example.passfashion.dto.Request.UserUpdateRequest;
import com.example.passfashion.dto.Response.AddressResponse;
import com.example.passfashion.dto.Response.UserResponse;
import com.example.passfashion.model.Address;
import com.example.passfashion.model.User;
import com.example.passfashion.repository.AddressRepository;
import com.example.passfashion.repository.UserRepository;
import com.example.passfashion.service.UserService;
import com.example.passfashion.dto.Request.ForgotPasswordRequest;
import com.example.passfashion.dto.Request.ResetPasswordRequest;
import com.example.passfashion.dto.Request.VerifyCodeRequest;
import com.example.passfashion.dto.Response.VerifyCodeResponse;

import jakarta.validation.Valid;
import jakarta.mail.MessagingException;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    // Xử lý Đăng nhập, đăng xuất
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/login")
    public UserResponse login(@Valid @RequestBody LoginRequest request) {
        return userService.login(request);
    }

     @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // ==================================================================
    @PostMapping("/resend-code")
    public ResponseEntity<String> resendCode(@RequestBody ForgotPasswordRequest request) throws MessagingException {
        userService.resendCode(request.getEmail());
        return ResponseEntity.ok("Mã xác nhận mới đã được gửi");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) throws MessagingException {
        userService.sendPasswordResetEmail(request.getEmail());
        return ResponseEntity.ok("Email reset mật khẩu đã được gửi");
    }

    @PostMapping("/verify-code")
    public ResponseEntity<?> verifyCode(@RequestBody VerifyCodeRequest request) {
        boolean isValid = userService.verifyCode(request.getEmail(), request.getCode());
        return ResponseEntity.ok(new VerifyCodeResponse(isValid));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        userService.resetPassword(request.getEmail(), request.getPassword());
        return ResponseEntity.ok("Mật khẩu đã được cập nhật");
    }
    // ==========================================================================

    @PutMapping("/{id}")
    public boolean updateUserById(@PathVariable long id, @RequestBody UserUpdateRequest request) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with id: " + id);
        }
        User user = userOptional.get();
        user.setName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setBirthday(request.getBirthday());
        user.setPhone(request.getPhone());

        // Save và return true nếu không lỗi
        userRepository.save(user);
        return true;
    }

    @GetMapping("/address-list/{userId}")
    public List<AddressResponse> getUserAddress(@PathVariable long userId) {
        List<Address> list = addressRepository.getAddressesByUserId(userId);
        List<AddressResponse> response = new ArrayList<>();
        for (Address address : list) {
            response.add(new AddressResponse(address.getId(), address.getProvince(), address.getDistrict(),
                    address.getWard(), address.getDetail(), address.getPhone()));
        }
        return response;
    }

    @PutMapping("/address-update")
    public boolean updateUserAddress(@RequestBody AddressUpdateRequest request) {
        Optional<Address> addressOptional = addressRepository.findById(request.getId());
        if (addressOptional.isEmpty()) {
            throw new RuntimeException("Address not found with id: " + request.getId());
        }
        Address address = addressOptional.get();
        address.setProvince(request.getProvince());
        address.setDistrict(request.getDistrict());
        address.setWard(request.getWard());
        address.setDetail(request.getDetail());
        address.setPhone(request.getPhone());

        // Save và return true nếu không lỗi
        addressRepository.save(address);
        return true;
    }

    @GetMapping("/me")
    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

    @GetMapping("/spending/{userId}")
    public ResponseEntity<?> getSpendingData(@PathVariable long userId) {
        try {
            return ResponseEntity.ok(orderService.getSpendingData(userId));
        } catch (Exception e) {
            System.err.println("Error fetching data: " + e.getMessage());
            return ResponseEntity.status(500).body("Failed to fetch data: " + e.getMessage());
        }
    }
}
