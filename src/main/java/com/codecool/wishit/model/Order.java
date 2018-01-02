package com.codecool.wishit.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    // Basic fields
    private int id;
    private int userId;
    private Status status;
    private List<LineItem> items;
    private Date closedDate;

    // Checkout (billing/shipping) information
    private float totalPrice;
    private String fullName;
    private String email;
    private String phone;
    private String billingCountry;
    private String billingCity;
    private String billingZipCode;
    private String billingAddress;
    private String shippingCountry;
    private String shippingCity;
    private String shippingZipCode;
    private String shippingAddress;
    private String orderLogFilename;

    public Order(int id, int userId, String orderLogFileName) {
        this.id = id;
        this.userId = userId;
        this.items = new ArrayList<>();
        this.status = Status.NEW;
        this.orderLogFilename = orderLogFileName;
    }

    public Order(int id, int userId, Status status, List<LineItem> items, Date closedDate, float totalPrice, String fullName, String email,
                 String phone, String billingCountry, String billingCity, String billingZipCode, String billingAddress,
                 String shippingCountry, String shippingCity, String shippingZipCode, String shippingAddress, String orderLogFilename) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.items = items;
        this.closedDate = closedDate;
        this.totalPrice = totalPrice;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.billingCountry = billingCountry;
        this.billingCity = billingCity;
        this.billingZipCode = billingZipCode;
        this.billingAddress = billingAddress;
        this.shippingCountry = shippingCountry;
        this.shippingCity = shippingCity;
        this.shippingZipCode = shippingZipCode;
        this.shippingAddress = shippingAddress;
        this.orderLogFilename = orderLogFilename;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public Status getStatus() {
        return status;
    }

    public List<LineItem> getItems() {
        return items;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public String getBillingZipCode() {
        return billingZipCode;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public String getShippingZipCode() {
        return shippingZipCode;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public String getOrderLogFilename() {
        return orderLogFilename;
    }

    public Integer countCartItems() {
        return this.items.size();
    }
}
