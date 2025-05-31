package com.example.passfashion.dto.Response;

import com.example.passfashion.model.Image;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String name;
    private String email;
    private LocalDate birthday;
    private String phone;
    private String imageUrl;

}
