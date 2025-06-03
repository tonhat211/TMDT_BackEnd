package com.example.passfashion.dto.Response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class AddressResponse {
    private long id;
    private String province;
    private String district;
    private String ward;
    private String detail;
    private String phone;

    public AddressResponse(long id, String province, String district, String ward, String detail, String phone) {
        this.id = id;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.detail = detail;
        this.phone = phone;
    }
}
