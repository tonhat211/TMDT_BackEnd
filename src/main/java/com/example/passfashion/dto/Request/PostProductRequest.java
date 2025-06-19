package com.example.passfashion.dto.Request;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostProductRequest {
    @NotBlank(message = "Tên sản phẩm không được rỗng")
    private String name;
    @Positive(message = "Giá sản phẩm phải lớn hơn 0")
    private double price;
    private double salePrice;
    @NotBlank(message = "Tên danh mục không được trống")
    private String categoryTitle;
    @Positive(message = "Người dùng không hợp lệ")
    private long userId;
    @Min(value = 1, message = "Số lượng tối thiểu là 1")
    private int qty;
    private String description;
    @Size(max = 5, message = "Tối đa 5 ảnh cho mỗi sản phẩm")
    private List<String> imageUrls; // Tối đa 5 URL
    private String material;
    private boolean negotiable;
    private String brand;
    private String condition;
    private String pickupAddress;
    private boolean termsAccepted;

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public double getSalePrice() {
        return this.salePrice;
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

    public String getCategoryTitle() {
        return categoryTitle;
    }

}
