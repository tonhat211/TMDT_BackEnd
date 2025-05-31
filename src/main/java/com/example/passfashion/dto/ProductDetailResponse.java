package com.example.passfashion.dto;

import com.example.passfashion.model.*;
import com.example.passfashion.service.Constant;

import java.util.ArrayList;
import java.util.List;

// dung trong productDetail: hien thi chi tiet san pham
public class ProductDetailResponse {
    private Product product;
    private Category category;
    private List<String> images = new ArrayList<String>();
    private boolean isSold;
    private User owner;
    private List<CommentResponse> comments;

    public ProductDetailResponse(Product product, Category category, List<Image> images, User user,
            List<CommentResponse> comments) {
        this.product = new Product();
        this.product.setId(product.getId());
        this.product.setName(product.getName());
        this.product.setDescription(product.getDescription());
        this.product.setPrice(product.getPrice());
        this.product.setQty(product.getQty());
        this.category = (category);
        if (images != null && !images.isEmpty()) {
            for (Image image : images) {
                this.images.add(Constant.PRODUCT_IMG_DIR + "/" + image.getUrl().trim());
            }
        }
        if (product.getIsSold() == 1)
            this.isSold = true;
        else
            this.isSold = false;

        owner = new User();
        owner.setId(user.getId());
        owner.setName(user.getName());
        owner.setRating(user.getRating());
        owner.setTotalReview(user.getTotalReview());
        if (user.getImage() != null)
            owner.setAvatar(Constant.AVATAR_IMG_DIR + "/" + user.getImage().getUrl());
        //
        this.comments = comments;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<CommentResponse> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponse> comments) {
        this.comments = comments;
    }
}
