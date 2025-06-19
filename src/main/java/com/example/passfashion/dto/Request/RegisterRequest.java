package com.example.passfashion.dto.Request;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
    @Email(message = "Email không hợp lệ")
    @NotBlank(message = "Email không được để trống")
    private String email;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 8, message = "Mật khẩu phải có ít nhất 8 ký tự")
    String pwd;
  
    @Size(min = 8, message = "Mật khẩu xác nhận phải có ít nhất 8 ký tự")
    String confirmPwd;
  
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^\\d{10}$", message = "Số điện thoại không hợp lệ")

    @Size(min = 10, max = 15, message = "Số điện thoại không hợp lệ")
    String phone;

    // Getter methods
    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

    public String getPhone() {
        return phone;
    }

    // setter
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
