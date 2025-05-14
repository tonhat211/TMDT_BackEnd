package com.example.passfashion.dto;

public class CategoryCountResponse {
    private String link;
    private long count;

    public CategoryCountResponse(String link, long count) {
        this.link = link;
        this.count = count;

    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public long getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
