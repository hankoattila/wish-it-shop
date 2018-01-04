package com.codecool.wishit.model;

import java.util.Currency;

public class Product {

    private int id;
    private String name;
    private String type;
    private String description;
    private String imageFileName;
    private float defaultPrice;
    private Currency defaultCurrency;
    private boolean reserved;

    public Product() {
    }

    public Product(String name, String description, float defaultPrice, String imageFileName) {
        this.name = name;
        this.description = description;
        this.defaultPrice = defaultPrice;
        this.imageFileName = imageFileName;
        this.defaultCurrency = Currency.getInstance("HUF");
    }

    public Product(String name, String type, String description, String imageFileName, float defaultPrice) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.imageFileName = imageFileName;
        this.defaultPrice = defaultPrice;
        this.defaultCurrency = Currency.getInstance("HUF");
        this.reserved = true;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getDefaultPrice() {
        return defaultPrice;
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public String getPrice() {
        return String.valueOf(this.defaultPrice) + " " + this.defaultCurrency.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public void setDefaultPrice(float defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    @Override
    public String toString() {
        return "Product{name=" + name + "}";
    }
}
