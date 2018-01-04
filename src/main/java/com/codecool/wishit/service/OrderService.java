package com.codecool.wishit.service;

import com.codecool.wishit.model.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    public Order getOpenOrder(User user) {

        if (user == null) {
            return null;
        }

        final String URI = String.format("http://192.168.161.184:8080/order/%d", user.getUserId());

        String response;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        try {
            response = restTemplate.exchange(URI, HttpMethod.GET, entity, String.class).getBody();
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            /* DUMMY IMPLEMENTATION */
            List<LineItem> lineItems = new ArrayList<>();
            lineItems.add(new LineItem(1, 1, "Light Saber", "lightsaber.jpg", 5000.0f, "USD"));
            lineItems.add(new LineItem(1, 2, "Fight Club Soap", "soap.jpg", 100.0f, "USD"));
            Order dummyOrder = new Order(lineItems, 555.0f);
            dummyOrder.setStatus(Status.NEW);
            return dummyOrder;
        }

        Gson gson = new Gson();
        return gson.fromJson(response, Order.class);
    }

    public String handleAddToCart(User user, Product product) {

        if (user == null || product == null) {
            return null;
        }

        final String URI = "http://192.168.161.184:8080/api/add-to-cart";

        String response;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", user.getUserId());
        map.put("product", product);
        HttpEntity<Map> entity = new HttpEntity<>(map , headers);

        try {
            response = restTemplate.exchange(URI, HttpMethod.POST, entity, String.class).getBody();
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            return null;
        }

        System.out.println(response);
        System.out.println(response.length());
        return response;
    }

    public Order handleRemoveFromCart(User user, Product product) {

        if (user == null || product == null) {
            return null;
        }

        Gson gson = new Gson();

        final String URI = "http://192.168.161.184:8080/api/remove-from-cart";

        String response;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", user.getUserId());
        map.put("product", product);
        HttpEntity<Map> entity = new HttpEntity<>(map, headers);

        try {
            response = restTemplate.exchange(URI, HttpMethod.POST, entity, String.class).getBody();
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            return null;
        }

        System.out.println(response);
        return gson.fromJson(response, Order.class);
    }

    public void setOrderStatusToPaid(Integer orderId) {

        final String URI = "http://192.168.161.184:8080/api/set-order-to-paid";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("orderId", orderId);
        HttpEntity<MultiValueMap> entity = new HttpEntity<>(formData , headers);

        try {
            restTemplate.exchange(URI, HttpMethod.POST, entity, String.class).getBody();
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
        }
    }

}
