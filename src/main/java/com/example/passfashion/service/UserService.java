package com.example.passfashion.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
import com.example.passfashion.utils.VerificationCode;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;
    private final Map<String, VerificationCode> verificationCodes = new ConcurrentHashMap<>();

    public UserResponse login(@Valid LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email không tồn tại"));

        if (!passwordEncoder.matches(request.getPwd(), user.getPwd())) {
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
        if (!request.getPwd().equals(request.getConfirmPwd())) {
            throw new RuntimeException("Mật khẩu và mật khẩu xác nhận không khớp");
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

    // ========================================================================

    public void sendPasswordResetEmail(String email) throws MessagingException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email không tồn tại"));

        String code = String.format("%04d", new Random().nextInt(10000));
        verificationCodes.put(email, new VerificationCode(code, LocalDateTime.now().plusSeconds(120)));

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email);
        helper.setSubject("Mã xác nhận đặt lại mật khẩu");
        helper.setText("Mã xác nhận của bạn là: " + code + "\nMã có hiệu lực trong 2 phút.", true);
        mailSender.send(message);
    }

    public boolean verifyCode(String email, String code) {
        VerificationCode verificationCode = verificationCodes.get(email);
        if (verificationCode == null || verificationCode.isExpired()) {
            verificationCodes.remove(email);
            return false;
        }
        boolean isValid = verificationCode.getCode().equals(code);
        if (isValid) {
            verificationCodes.remove(email);
        }
        return isValid;
    }

    public void resendCode(String email) throws MessagingException {
        verificationCodes.remove(email); // Xóa mã cũ
        sendPasswordResetEmail(email); // Gửi mã mới
    }

    public void resetPassword(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email không tồn tại"));
        user.setPwd(passwordEncoder.encode(password));
        userRepository.save(user);
    }
    // ========================================================================

    // 👉 Đây là method chuyển đổi User → UserResponse, viết nội bộ trong service
    public UserResponse convertToUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setBirthday(user.getBirthday());
        response.setPhone(user.getPhone());
        response.setAvatar(user.getAvatar());
        response.setRole(user.getRole());
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
