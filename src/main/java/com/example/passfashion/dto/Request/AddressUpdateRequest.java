package com.example.passfashion.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressUpdateRequest {
    private long id;
    private String province;
    private String district;
    private String ward;
    private String detail;
    private String phone;

    // Getter
    public long getId() {
        return id;
    }

    public String getProvince() {
        return province;
    }

    public String getDistrict() {
        return district;
    }

    public String getWard() {
        return ward;
    }

    public String getDetail() {
        return detail;
    }

    public String getPhone() {
        return phone;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
