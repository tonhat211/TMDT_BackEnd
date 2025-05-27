package com.example.passfashion.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="pwd", nullable = false)
    private String pwd;

    @Column(name="birthday", nullable = true)
    private LocalDate birthday;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses=new ArrayList<>();

    @Column(name="sold_order_qty", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int soldOrderQty;

    @Column(name="rating", nullable = false, columnDefinition = "DOUBLE DEFAULT 0")
    private double rating;

    @Column(name="total_review", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int totalReview;

    @Column(name="is_deleted", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int isDeleted;

    @Transient
    private String avatar;


    public User() {
    }

    public User(long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getSoldOrderQty() {
        return soldOrderQty;
    }

    public void setSoldOrderQty(int soldOrderQty) {
        this.soldOrderQty = soldOrderQty;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getTotalReview() {
        return totalReview;
    }

    public void setTotalReview(int totalReview) {
        this.totalReview = totalReview;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
}
