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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

    @GetMapping(value = Path.Web.UPLOAD_PRODUCT)
    public String uploadProductPage() {
        return Path.Template.UPLOAD_PRODUCT;
    }

    @PostMapping(value = Path.Web.UPLOAD_PRODUCT + "/upload")
    public String uploadProductToDB(Model model,
                                    @RequestParam("product-name") String name,
                                    @RequestParam("category") String type,
                                    @RequestParam("description") String description,
                                    @RequestParam("price") float price,
                                    @RequestParam("image-file") MultipartFile file,
                                    RedirectAttributes redirectAttributes) {
        System.out.println("upload");
        System.out.println(file);

        File imageFile = new File("src/main/resources/static/img/" + file.getOriginalFilename());
        try {
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            byte[] strToBytes = file.getBytes();
            outputStream.write(strToBytes);

            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Product product = new Product(name, type, description, imageFile.getName(), price);
        productService.saveProduct(product, 2);
        return "redirect:/";
    }

}
