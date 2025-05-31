package com.example.passfashion.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="pwd", nullable = false)
    private String pwd;

    @Column(name="birthday", nullable = true)
    private LocalDate birthday;

    @Column(name = "phone", nullable = true)
    private String phone;

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

    @PrePersist
    public void prePersist() {
        if (this.name == null || this.name.trim().isEmpty()) {
            this.name = "user" + UUID.randomUUID().toString().substring(0, 8);
        }
    }
    public User(long id) {
        this.id = id;
    }

}
