package com.example.passfashion.dto.Response;

import com.example.passfashion.model.Image;
import com.example.passfashion.model.Product;
import com.example.passfashion.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// dung trong product,... hien thi productItem
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminProductResponse {
    private long id;
    private String name;
    private double price;
    private String thumbnail;
    private String description;
    private String pickUpInfo;
    private long userId;
    private String userName;
    private String userAvatar;
    private boolean isSold;

    public AdminProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        if (product.getImages() != null && !product.getImages().isEmpty()) {
            this.thumbnail = product.getImages().get(0).getUrl();
        } else {
            this.thumbnail = "uploads/default.png";
        }
        this.description = product.getDescription();
        this.pickUpInfo = product.getPickupAddress();
        User user = product.getUser();
        this.userId = user.getId();
        this.userName = user.getName();
        this.userAvatar = user.getAvatar()!=null ? user.getAvatar() : "uploads/default.png";
        this.isSold = product.getIsSold();
    }


}
