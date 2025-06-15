package com.example.passfashion.dto.Request;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class PostProductRequest {
    private long id;
    private String name;
    private double price;
    private double salePrice;
    private long categoryId;
    private String categoryTitle;
    private long userId;
    private int qty;
    private String description;
    private List<String> imageUrls; // Tối đa 5 URL
    private String material;
    private boolean negotiable;
    private String brand;
    private String condition;
    private String pickupAddress;
    private boolean termsAccepted;
    private boolean isSold;
    private boolean isDeleted;
    private LocalDateTime createdAt;

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

    public boolean isTermsAccepted() {
        return this.termsAccepted;
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

    public void setTermsAccepted(final boolean termsAccepted) {
        this.termsAccepted = termsAccepted;
    }

    public boolean isSold() {
        return this.isSold;
    }

    public void setSold(final boolean isSold) {
        this.isSold = isSold;
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }

    public void setDeleted(final boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public PostProductRequest() {
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

}
