package com.example.passfashion.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "password_reset_tokens")
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token; // Chuỗi token ngẫu nhiên để reset mật khẩu.

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Liên kết với người dùng yêu cầu reset.

    @Column(nullable = false)
    private LocalDateTime expiryDate; // Thời gian hết hạn của token (ví dụ: 24 giờ).

    public PasswordResetToken(String token, User user, LocalDateTime expiryDate) {
        this.token = token;
        this.user = user;
        this.expiryDate = expiryDate;
    }
}
