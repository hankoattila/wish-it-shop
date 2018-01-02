package com.codecool.wishit.service;

import com.codecool.wishit.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    public List<Product> getAllProducts() {

        // TODO: communicate with products-microservice to get all products (which are not yet sold)

        // TODO: ONLY DUMMY IS BEING RETURNED NOW, TO BE PROPERLY IMPLEMENTED!
        List<Product> products = new ArrayList<>();
        products.add(new Product("Luke's Lightsaber", "Fantastic price. Good ecosystem and controls. Helpful technical support.", 49.9f, "lightsaber.jpg"));
        products.add(new Product("Bud Spencer's pan", "Old tool from az old friend. Old tool from az old friend.", 20.9f, "bud_pan.jpg"));
        products.add(new Product("The last soap from Fight Club", "Be clean. Be a fighter. Be a weapon.", 25.9f, "soap.jpg"));

        return products;
    }

}
