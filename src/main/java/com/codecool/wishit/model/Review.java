package com.codecool.wishit.model;

public class Review {
    public String id;
    public Integer userId;
    public Integer productId;
    public String userName;
    public String text;
    public Integer star;

    public Review() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", productId=" + productId +
                ", userName='" + userName + '\'' +
                ", text='" + text + '\'' +
                ", star=" + star +
                '}';
    }
}
