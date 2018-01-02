package com.codecool.wishit.model;

import java.util.Currency;

public class Product {

    private String name;
    private String description;
    private float defaultPrice;
    private Currency defaultCurrency;
    private String imageFileName;
    // private ProductCategory productCategory;


    public Product(String name, String description, float defaultPrice, String imageFileName) {
        this.name = name;
        this.description = description;
        this.defaultPrice = defaultPrice;
        this.imageFileName = imageFileName;
        this.defaultCurrency = Currency.getInstance("USD");
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

}
