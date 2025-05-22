package com.example.passfashion.dto;

import com.example.passfashion.model.Image;
import com.example.passfashion.service.Constant;

import java.util.List;

public class BasicProductResponse {
    private long id;
    private String name;
    private double price;
    private String thumbnail;
    private boolean isSold;

    public BasicProductResponse(long id, String name, double price, List<Image> images, int isSold) {
        this.id = id;
        this.name = name;
        this.price = price;
        if (images != null && !images.isEmpty()) {
            this.thumbnail = Constant.PRODUCT_IMG_DIR+"/"+images.get(0).getUrl();
        }
        if(isSold==1) this.isSold = true; else this.isSold = false;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
