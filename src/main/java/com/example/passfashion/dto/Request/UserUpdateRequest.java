package com.example.passfashion.dto.Request;

import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    private String fullName;
    private String email;
    private String phone;
    private LocalDate birthday;

}
