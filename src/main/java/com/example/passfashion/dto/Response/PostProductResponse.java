package com.example.passfashion.dto.Response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class PostProductResponse {
    private long id;
    private String name;
    private double price;
    private double salePrice;
    private long categoryId;
    private String categoryTitle;
    private long userId;
    private int qty;
    private String description;
    private List<String> imageUrls;
    private boolean isSold;
    private boolean isDeleted;
    private LocalDateTime createdAt;
    private String material;
    private boolean negotiable;
    private String brand;
    private String condition;
    private String pickupAddress;
    private boolean termsAccepted;

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public double getSalePrice() {
        return this.salePrice;
    }

    public long getCategoryId() {
        return this.categoryId;
    }

    public String getCategoryTitle() {
        return this.categoryTitle;
    }

    public long getUserId() {
        return this.userId;
    }

    public int getQty() {
        return this.qty;
    }

    public String getDescription() {
        return this.description;
    }

    public List<String> getImageUrls() {
        return this.imageUrls;
    }

    public boolean isSold() {
        return this.isSold;
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public String getMaterial() {
        return this.material;
    }

    public boolean isNegotiable() {
        return this.negotiable;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getCondition() {
        return this.condition;
    }

    public String getPickupAddress() {
        return this.pickupAddress;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public void setSalePrice(final double salePrice) {
        this.salePrice = salePrice;
    }

    public void setCategoryId(final long categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryTitle(final String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public void setUserId(final long userId) {
        this.userId = userId;
    }

    public void setQty(final int qty) {
        this.qty = qty;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setImageUrls(final List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public void setIsSold(final boolean isSold) {
        this.isSold = isSold;
    }

    public void setIsDeleted(final boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setMaterial(final String material) {
        this.material = material;
    }

    public void setNegotiable(final boolean negotiable) {
        this.negotiable = negotiable;
    }

    public void setBrand(final String brand) {
        this.brand = brand;
    }

    public void setCondition(final String condition) {
        this.condition = condition;
    }

    public void setPickupAddress(final String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

}
