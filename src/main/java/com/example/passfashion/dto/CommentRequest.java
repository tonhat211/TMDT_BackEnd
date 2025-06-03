package com.example.passfashion.dto;

public class CommentRequest {
    private long userId;
    private long productId;
    private String content;
    private long parentId;
    private int level;

    public CommentRequest(long userId, long productId, String content, long parentId, int level) {
        this.userId = userId;
        this.productId = productId;
        this.content = content;
        this.parentId = parentId;
        this.level = level;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
