package com.codecool.wishit.model;

import java.util.Currency;

public class LineItem {

    private int orderId;
    private int productId;
    private String productName;
    private String productImage;

    private int quantity;
    private float actualPrice;
    private Currency currency;
    private float subTotalPrice;

    public LineItem(int orderId, int productId, String productName, String productImage, int quantity, float actualPrice, Currency currency) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.quantity = quantity;
        this.actualPrice = actualPrice;
        this.subTotalPrice = quantity * actualPrice;
        this.currency = currency;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getActualPrice() {
        return actualPrice;
    }

    public Currency getCurrency() {
        return currency;
    }

    public float getSubTotalPrice() {
        return subTotalPrice;
    }

}
