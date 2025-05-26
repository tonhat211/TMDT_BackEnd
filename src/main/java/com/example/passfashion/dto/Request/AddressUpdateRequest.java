package com.example.passfashion.dto.Request;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressUpdateRequest {
    private long id;
    private String province ;
    private String district;
    private String ward ;
    private String detail ;
    private String phone;
}
