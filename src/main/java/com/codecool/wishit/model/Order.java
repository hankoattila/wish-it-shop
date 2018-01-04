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

    public Order() {
    }

    public Order(int id, int userId, String orderLogFileName) {
        this.id = id;
        this.userId = userId;
        this.items = new ArrayList<>();
        this.status = Status.NEW;
        this.orderLogFilename = orderLogFileName;
    }

    public Order(List<LineItem> items, float totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setItems(List<LineItem> items) {
        this.items = items;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public void setBillingZipCode(String billingZipCode) {
        this.billingZipCode = billingZipCode;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public void setShippingZipCode(String shippingZipCode) {
        this.shippingZipCode = shippingZipCode;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setOrderLogFilename(String orderLogFilename) {
        this.orderLogFilename = orderLogFilename;
    }

    public Integer countCartItems() {
        return this.items.size();
    }

}
