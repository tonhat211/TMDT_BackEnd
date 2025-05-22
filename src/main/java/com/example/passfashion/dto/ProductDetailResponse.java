package com.example.passfashion.dto;

import com.example.passfashion.model.Comment;
import com.example.passfashion.model.Image;
import com.example.passfashion.model.Product;
import com.example.passfashion.model.User;
import com.example.passfashion.service.Constant;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailResponse {
    private Product product;
    private List<String> images = new ArrayList<String>();
    private boolean isSold;
    private User owner;
    private List<Comment> comments;
    public ProductDetailResponse(Product product, List<Image> images, User user, List<Comment> comments) {
        this.product = new Product();
        this.product.setId(product.getId());
        this.product.setName(product.getName());
        this.product.setDescription(product.getDescription());
        this.product.setPrice(product.getPrice());
        if (images != null && !images.isEmpty()) {
            for (Image image : images) {
                this.images.add(image.getUrl());
            }
        }
        if(product.getIsSold()==1) this.isSold = true; else this.isSold = false;

        owner = new User();
        owner.setId(user.getId());
        owner.setName(user.getName());
        owner.setRating(user.getRating());
        owner.setTotalReview(user.getTotalReview());
        if(user.getImage()!=null)
            owner.setImageUrl(Constant.AVATAR_IMG_DIR+"/"+ user.getImage().getUrl());
//
        this.comments = comments;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
