package com.codecool.wishit.service;

import com.codecool.wishit.model.Order;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {

    private final String LOCAL_URL = "http://192.168.161.176:60000/";
    private final String HEROKU_URL = "https://intense-refuge-32447.herokuapp.com/";

    public List<String> getMethods() {

        final String URI = HEROKU_URL + "payment-methods";

        String response;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        try {
            response = restTemplate.exchange(URI, HttpMethod.GET, entity, String.class).getBody();
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            return null;
        }
        System.out.println(response);

        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();

        if (jsonObject == null || jsonObject.get("methods") == null) {
            return null;
        }

        Gson gson = new Gson();
        return gson.fromJson(jsonObject.get("methods"), ArrayList.class);
    }

    public void sendOrderInfo(Order order) {

        final String URI = HEROKU_URL + "order-info";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        Map<String, Object> orderInfo = new HashMap<>();
        // TODO: 1/4/18 use the commented part instead of fix values
        orderInfo.put("orderId", 123); // order.getId()
        orderInfo.put("totalPrice", 666.0f); // order.getTotalPrice()

        HttpEntity<Map> entity = new HttpEntity<>(orderInfo, headers);

        try {
            restTemplate.exchange(URI, HttpMethod.POST, entity, String.class);
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
