package com.example.passfashion.service;

public class Constant {
    public static final String PRODUCT_IMG_DIR = "images/products";
    public static final String AVATAR_IMG_DIR = "images/avatars";
    public static final String THUMBNAIL_IMG_DIR = "images/thumbnails";
    public static final String ICON_IMG_DIR = "images/icons";

    public static final String PAGE_SIZE_STRING = "10";
    // hash password
    public static String hashPassword(String password) {
        HashAlgorism hashAlgorism = new HashAlgorism();
        try {
            return hashAlgorism.hash(password);
        } catch (Exception e) {
            return "null";
        }
    }

    public static final String ADMIN_GRANT = "ADMIN";


}
