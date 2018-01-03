package com.codecool.wishit.controller;

import com.codecool.wishit.model.Order;
import com.codecool.wishit.model.Product;
import com.codecool.wishit.model.User;
import com.codecool.wishit.service.OrderService;
import com.codecool.wishit.service.ProductService;
import com.codecool.wishit.service.UserService;
import com.codecool.wishit.utils.Path;
import com.codecool.wishit.utils.RequestUtil;
import com.codecool.wishit.utils.SessionData;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {

    private SessionData sessionData;
    private UserService userService;
    private OrderService orderService;
    private RequestUtil requestUtil;
    private ProductService productService;

    public ProductController(SessionData sessionData, UserService userService, OrderService orderService, RequestUtil requestUtil, ProductService productService) {
        this.sessionData = sessionData;
        this.userService = userService;
        this.orderService = orderService;
        this.requestUtil = requestUtil;
        this.productService = productService;
    }

    @ModelAttribute
    public void addAttributes(Model model) {

        User user = sessionData.getUser();
        Order order = orderService.getOpenOrder(user);

        model.addAttribute("loggedIn", user != null);
        model.addAttribute("userName", user != null ? user.getFullName() : null);
        model.addAttribute("orderStatus", order != null ? order.getStatus().toString() : null);
        model.addAttribute("cartItems", order != null ? order.countCartItems() : 0);
    }

    @GetMapping(value = Path.Web.PRODUCTS)
    public String serveProductsPage(Model model) {

        List<Product> products = productService.getAllProducts();
        List<String> types = productService.getAllTypes();
        model.addAttribute("products", products);
        model.addAttribute("types", types);
        return Path.Template.PRODUCTS;
    }

    @GetMapping(value = Path.Web.PRODUCTS + "/filter")
    public String filterProductsPage(Model model,
                                    @RequestParam(value = "category") String categoryFilter) {

        if (categoryFilter.equals("")) {
            return "redirect:/";
        }
        List<Product> products = productService.getFilteredProducts(categoryFilter);
        List<String> types = productService.getAllTypes();
        model.addAttribute("products", products);
        model.addAttribute("types", types);
        model.addAttribute("categoryFilter", categoryFilter);

        return Path.Template.PRODUCTS;
    }

}
