package com.example.passfashion.model;

import com.example.passfashion.utils.VietnameseUtils;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false, columnDefinition = "DOUBLE DEFAULT 0")
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "qty", columnDefinition = "INT DEFAULT 1")
    private int qty;

    @Column(name = "description", columnDefinition = "JSON")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "image_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private List<Image> images=new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments=new ArrayList<>();

    @Column(name = "is_sold", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int isSold;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int isDeleted;

    @Column(name = "created_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", nullable = false, updatable = false, insertable = false)
    private LocalDateTime createdAt;


    @Column(name = "unsigned_name", nullable = true)
    private String unsignedName;

    @PrePersist
    @PreUpdate
    public void prepare() {
        this.unsignedName = VietnameseUtils.removeVietnameseDiacritics(this.name).toLowerCase();
    }


    public String getUnsignedName() {
        return unsignedName;
    }

    public void setUnsignedName(String unsignedName) {
        this.unsignedName = unsignedName;
    }

    public Product() {
    }

    public Product(long id) {
        this.id = id;
    }

    public Product(long id, String name, double price, List<Image> images) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.images = images;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getIsSold() {
        return isSold;
    }

    public void setIsSold(int isSold) {
        this.isSold = isSold;
    }
}
