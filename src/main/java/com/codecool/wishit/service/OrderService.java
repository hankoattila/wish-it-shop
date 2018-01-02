package com.codecool.wishit.service;

import com.codecool.wishit.model.Order;
import com.codecool.wishit.model.User;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public Order getOpenOrder(User user) {

        if (user == null) {
            return null;
        }

        // TODO: communicate with order-microservice to get the currently open order (cart) for user

        return null;
    }

}
