package com.example.passfashion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.passfashion.model.User;
import com.example.passfashion.model.enums.Role;
import com.example.passfashion.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Kiểm tra xem tài khoản admin đã tồn tại chưa
        if (userRepository.findByEmail("admin@example.com").isEmpty()) {
            User admin = new User();
            admin.setEmail("admin@example.com");
            admin.setPwd(passwordEncoder.encode("admin")); // Mã hóa mật khẩu
            admin.setName("Admin");
            admin.setRole(Role.ADMIN); // Gán vai trò ADMIN
            admin.setDeleted(false);

            userRepository.save(admin);
            System.out.println("Tài khoản Admin mặc định đã được tạo: admin@example.com");
        } else {
            System.out.println("Tài khoản Admin đã tồn tại.");
        }
    }
}