package com.codecool.wishit.service;

import com.codecool.wishit.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryService {

    public List<String> getMethods(User user) {

        // TODO: contact delivery-ms

        List<String> deliveryMethods = new ArrayList<>();
        deliveryMethods.add("Shipping Address");
        deliveryMethods.add("Post Point");

        return deliveryMethods;
    }

}
