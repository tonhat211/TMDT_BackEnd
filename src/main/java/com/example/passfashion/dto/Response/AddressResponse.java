package com.example.passfashion.dto.Response;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
    private long id;
    private String province ;
    private String district;
    private String ward ;
    private String detail ;
    private String phone ;
}
