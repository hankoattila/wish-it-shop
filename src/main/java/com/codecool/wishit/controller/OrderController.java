package com.codecool.wishit.controller;

import com.codecool.wishit.model.*;
import com.codecool.wishit.service.*;
import com.codecool.wishit.utils.Path;
import com.codecool.wishit.utils.RequestUtil;
import com.codecool.wishit.utils.SessionData;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    private SessionData sessionData;
    private UserService userService;
    private OrderService orderService;
    private RequestUtil requestUtil;
    private ProductService productService;
    private PaymentService paymentService;
    private DeliveryService deliveryService;

    public OrderController(SessionData sessionData, UserService userService, OrderService orderService,
                           RequestUtil requestUtil, ProductService productService, PaymentService paymentService,
                           DeliveryService deliveryService) {
        this.sessionData = sessionData;
        this.userService = userService;
        this.orderService = orderService;
        this.requestUtil = requestUtil;
        this.productService = productService;
        this.paymentService = paymentService;
        this.deliveryService = deliveryService;
    }

    @ModelAttribute
    public void addAttributes(Model model) {

        User user = sessionData.getUser();
        Order order = orderService.getOpenOrder(user);

        model.addAttribute("loggedIn", user != null);
        model.addAttribute("fullName", user != null ? user.getFullName() : null);
        model.addAttribute("orderStatus", order != null ? order.getStatus().toString() : null);
        model.addAttribute("cartItems", order != null ? order.countCartItems() : 0);
    }

    @PostMapping(value = Path.Web.ADD_TO_CART)
    public String handleAddToCart(@RequestParam("product_id") int productId) {

        User user = sessionData.getUser();

        Product product = productService.getProductById(productId);
        return orderService.handleAddToCart(user, product);
    }

    @PostMapping(value = Path.Web.REMOVE_FROM_CART)
    public String handleRemoveFromCart(@RequestParam("product_id") int productId) {

        User user = sessionData.getUser();

        Product product = productService.getProductById(productId);
        Order order = orderService.handleRemoveFromCart(user, product);

        Map<String, Object> newPrices = new HashMap<>();
        newPrices.put("total", order.getTotalPrice());
        newPrices.put("cartIsEmpty", order.getItems().isEmpty());

        Gson gson = new Gson();
        return gson.toJson(newPrices);
    }

    @GetMapping(value = Path.Web.CART)
    public String serveCartPage(Model model) {

        User user = sessionData.getUser();
        Order order = orderService.getOpenOrder(user);
        model.addAttribute("order", order);
        model.addAttribute("items", order != null ? order.getItems() : new ArrayList<LineItem>());
        model.addAttribute("grandTotal", order != null ? String.format("%.2f", order.getTotalPrice()) : 0);

        return Path.Template.CART;
    }

    @GetMapping(value = Path.Web.SUMMARY)
    public String serveSummaryPage(Model model) {

        User user = sessionData.getUser();
        Order order = orderService.getOpenOrder(user);
        model.addAttribute("order", order);

        // TODO: 1/4/18 payment.html refactor - subtotal and quantity removal

        List<String> paymentMethods = paymentService.getMethods();
        model.addAttribute("paymentMethods", paymentMethods);

        System.out.println(paymentMethods);
        return Path.Template.SUMMARY;
    }

    @PostMapping(value = Path.Web.PAYMENT)
    public String handlePaymentSelection(Model model, @RequestParam("paymentMethod") String paymentMethod) {

        User user = sessionData.getUser();
        Order order = orderService.getOpenOrder(user);
        model.addAttribute("order", order);

        if (paymentMethod.equals("PayPal")) {
            final String LOCAL_URL = "http://192.168.161.176:60000/";
            final String HEROKU_URL = "https://intense-refuge-32447.herokuapp.com/";
            paymentService.sendOrderInfo(order);

            return "redirect:" + HEROKU_URL + "payment/1";
        }

        // TODO: CASH SUCCESS PAGE - render cash.html with static content

        return Path.Template.CASH;
    }

    @GetMapping(value = Path.Web.SUCCESSFUL_TRANSACTION)
    public String serveSuccessfulTransactionPage(@RequestParam("orderId") Integer orderId,
                                                 @RequestParam("result") String result) {

        if (result.equals("success")) {
            orderService.setOrderStatusToPaid(orderId);
            return Path.Template.SUCCESSFUL_TRANSACTION;
        }

        // TODO: now only successful PayPal payment is possible, error not yet implemented.
        return Path.Template.SUCCESSFUL_TRANSACTION;
    }

    @GetMapping(value = Path.Web.CHECKOUT)
    public String serveCheckoutPage(Model model) {

        User user = sessionData.getUser();

        // TODO: get delivery methods from delivery-ms and pass to model!!!
        List<String> deliveryMethods = deliveryService.getMethods(user);

        model.addAttribute("user", user);
        model.addAttribute("deliveryMethods", deliveryMethods);

        return Path.Template.CHECKOUT;
    }

    @PostMapping(value = Path.Web.CHECKOUT)
    public String handleCheckoutPageSubmit(Model model) {

        User user = sessionData.getUser();

        // TODO: send data to deliver-ms, he will send ok/error...

        model.addAttribute("user", user);
        model.addAttribute("order", orderService.getOpenOrder(user));

        return "redirect:" + Path.Web.SUMMARY;
    }

}
