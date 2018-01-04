package com.codecool.wishit.service;

import com.codecool.wishit.model.Product;
import com.codecool.wishit.utils.Path;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    public List<Product> getAllProducts() {

        // TODO: communicate with products-microservice to get all products (which are not yet sold)
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Product[]> response =
                    restTemplate.getForEntity(Path.MicroServices.PRODUCT_SERVICE + "/products", Product[].class);
            return Arrays.asList(response.getBody());
        } catch (ResourceAccessException e) {
            System.out.println("Product Service is unavailable: " + e);
//            return null;

            // TODO: DUMMY! Delete it after everything is OK
            List<Product> products = new ArrayList<>();
            products.add(new Product("Luke's Lightsaber", "Fantastic price. Good ecosystem and controls. Helpful technical support.", 49.9f, "lightsaber.jpg"));
            products.add(new Product("Bud Spencer's pan", "Old tool from az old friend. Old tool from az old friend.", 20.9f, "bud_pan.jpg"));
            products.add(new Product("The last soap from Fight Club", "Be clean. Be a fighter. Be a weapon.", 25.9f, "soap.jpg"));

            return products;
        }
    }

    public List<Product> getFilteredProducts(String type) {

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Product[]> response =
                    restTemplate.getForEntity(Path.MicroServices.PRODUCT_SERVICE + "/products/type?typeName=" + type, Product[].class);
            return Arrays.asList(response.getBody());
        } catch (ResourceAccessException e) {
            System.out.println("Product Service is unavailable: " + e);
            // return null;

            // TODO: DUMMY! Delete it after everything is OK
            List<Product> products = new ArrayList<>();
            products.add(new Product("Luke's Lightsaber", "Fantastic price. Good ecosystem and controls. Helpful technical support.", 49.9f, "lightsaber.jpg"));
            products.add(new Product("Bud Spencer's pan", "Old tool from az old friend. Old tool from az old friend.", 20.9f, "bud_pan.jpg"));
            products.add(new Product("The last soap from Fight Club", "Be clean. Be a fighter. Be a weapon.", 25.9f, "soap.jpg"));

            return products;
        }
    }

    public void saveProduct(Product product, Integer userId) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            RestTemplate restTemplate = new RestTemplate();

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("name", product.getName());
            params.add("type", product.getType());
            params.add("description", product.getDescription());
            params.add("imageFileName", product.getImageFileName());
            params.add("defaultPrice", Float.toString(product.getDefaultPrice()));
            params.add("defaultCurrency", "HUF");

            HttpEntity<?> request = new HttpEntity<>(params, headers);
            restTemplate.postForLocation(Path.MicroServices.PRODUCT_SERVICE + "/" + userId + "/products", request);
        } catch (ResourceAccessException e) {
            System.out.println("Product Service is unavailable: " + e);
        }
    }

    public List<String> getAllTypes() {
        List<Product> products = getAllProducts();
        List<String> types = new ArrayList<>();
        for (Product product : products) {
            types.add(product.getType());
        }
        return types;
    }

    public Product getProductById(Integer productId) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Product> response =
                    restTemplate.getForEntity(Path.MicroServices.PRODUCT_SERVICE + "/products/" + productId, Product.class);
            return response.getBody();
        } catch (ResourceAccessException e) {
            System.out.println("Product Service is unavailable: " + e);
            return null;
        }

    }

}
