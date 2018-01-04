package com.codecool.wishit.service;

import com.codecool.wishit.model.Product;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    public List<Product> getAllProducts() {

        // TODO: communicate with products-microservice to get all products (which are not reserved)

        // TODO: ONLY DUMMY IS BEING RETURNED NOW, TO BE PROPERLY IMPLEMENTED!
        List<Product> products = new ArrayList<>();
        products.add(new Product("Luke's Lightsaber", "Fantastic price. Good ecosystem and controls. Helpful technical support.", 49.9f, "lightsaber.jpg"));
        products.add(new Product("Bud Spencer's pan", "Old tool from az old friend. Old tool from az old friend.", 20.9f, "bud_pan.jpg"));
        products.add(new Product("The last soap from Fight Club", "Be clean. Be a fighter. Be a weapon.", 25.9f, "soap.jpg"));

        return products;
    }

    public Product getProductById(int productId) {

        final String URI = String.format("http://wishit-product-service.herokuapp.com/products/%d", productId);

        String response;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        try {
            response = restTemplate.exchange(URI, HttpMethod.GET, entity, String.class).getBody();
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            return null;
        }

        Gson gson = new Gson();
        return gson.fromJson(response, Product.class);
    }

}
