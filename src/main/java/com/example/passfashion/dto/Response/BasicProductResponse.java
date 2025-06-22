package com.example.passfashion.dto.Response;

import com.example.passfashion.model.Image;
import com.example.passfashion.service.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// dung trong product,... hien thi productItem
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicProductResponse {
    private long id;
    private String name;
    private double price;
    private String thumbnail;
    private boolean isSold;

    public BasicProductResponse(long id, String name, double price, List<Image> images, boolean isSold) {
        this.id = id;
        this.name = name;
        this.price = price;
        if (images != null && !images.isEmpty()) {
            this.thumbnail = images.get(0).getUrl();
        } else {
            this.thumbnail = "uploads/default.png";
        }
        if (isSold)
            this.isSold = true;
        else
            this.isSold = false;
    }


}
