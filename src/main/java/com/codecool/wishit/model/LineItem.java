package com.codecool.wishit.model;

import java.util.Currency;

public class LineItem {

    private int orderId;
    private int productId;
    private String productName;
    private String productImage;
    private float actualPrice;
    private Currency currency;

    public LineItem(int orderId, int productId, String productName, String productImage, float actualPrice, String currency) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.actualPrice = actualPrice;
        this.currency = Currency.getInstance(currency);
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

    public float getActualPrice() {
        return actualPrice;
    }

    public Currency getCurrency() {
        return currency;
    }

}
