package com.codecool.wishit.utils;

public class Path {

    public static class Web {
        public static final String PRODUCTS = "";
        public static final String REGISTER = "register";
        public static final String LOGIN = "login";
        public static final String LOGOUT = "logout";
        public static final String USER_PROFILE = "profile";
        public static final String USER_PROFILE_SUCCESSFUL_EDIT = "profile?edited=successful";
        public static final String CART = "cart";
        public static final String SUMMARY = "summary";
        public static final String PAYMENT = "payment";
        public static final String ADD_TO_CART = "api/add-to-cart";
        public static final String REMOVE_FROM_CART = "api/remove-line-item";
        public static final String CHECKOUT = "checkout";
        public static final String SUCCESSFUL_TRANSACTION = "successful-transaction";
        public static final String UPLOAD_PRODUCT = "upload-product";
    }

    public static class Template {
        public final static String PRODUCTS = "products";
        public static final String REGISTER = "register";
        public static final String LOGIN = "login";
        public static final String LOGOUT = "logout";
        public static final String USER_PROFILE = "profile";
        public static final String CART = "cart";
        public static final String SUMMARY = "summary";
        public static final String CHECKOUT = "checkout";
        public static final String SUCCESSFUL_TRANSACTION = "success";
        public static final String CASH = "cash";
        public static final String UPLOAD_PRODUCT = "upload-product";
    }

    public static class MicroServices {
        public static final String PRODUCT_SERVICE = "http://wishit-product-service.herokuapp.com";
        public static final String REVIEW_SERVICE = "https://product-review-service.herokuapp.com";
    }

}
