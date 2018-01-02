package com.codecool.wishit.controller;

import com.codecool.wishit.model.Order;
import com.codecool.wishit.model.User;
import com.codecool.wishit.service.OrderService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private SessionData sessionData;
    private UserService userService;
    private OrderService orderService;
    private RequestUtil requestUtil;

    public UserController(SessionData sessionData, UserService userService, OrderService orderService, RequestUtil requestUtil) {
        this.sessionData = sessionData;
        this.userService = userService;
        this.orderService = orderService;
        this.requestUtil = requestUtil;
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

    @GetMapping(value = Path.Web.REGISTER)
    public String serveRegistrationPage(Model model){
        model.addAttribute("user", requestUtil.collectRegistrationData(new HashMap<>()));

        return Path.Template.REGISTER;
    }

    @GetMapping(value = Path.Web.LOGIN)
    public String serveLoginPage(){

        return Path.Template.LOGIN;
    }

    @PostMapping(value = Path.Web.REGISTER)
    public String handleRegistration(Model model, @RequestParam Map<String, String> formData){

        List<String> errorMessages = userService.validateRegistrationInput(formData);

        // IN CASE OF INVALID INPUT, RE-RENDER REGISTRATION PAGE
        if (errorMessages.size() > 0) {
            model.addAttribute("errors", errorMessages);
            model.addAttribute("user", formData);

            return Path.Template.REGISTER;
        }

        return "redirect:" + Path.Web.LOGIN;
    }

    @PostMapping(Path.Web.LOGIN)
    public String handleLogin(@RequestParam Map<String, String> form, Model model) {

        Map<String, String> inputData = requestUtil.collectLoginData(form);
        User user = userService.validateLoginCredentials(inputData);

        // INVALID LOGIN CREDENTIALS - RE-RENDER LOGIN PAGE
        if (user == null) {
            model.addAttribute("errors", userService.getInvalidLoginCredsErrorMessage());

            return Path.Template.LOGIN;
        }

        // SUCCESSFUL LOGIN - ADD TO SESSION AND REDIRECT TO PRODUCTS PAGE
        sessionData.setUser(user);

        return "redirect:" + Path.Web.PRODUCTS;
    }

    @GetMapping(Path.Web.LOGOUT)
    public String handleLogout() {

        sessionData.clear();
        return "redirect:" + Path.Web.PRODUCTS;
    }

    @GetMapping(value = Path.Web.USER_PROFILE)
    public String serveUserProfile(Model model, @RequestParam(name = "edited", required = false) String edited){

        User user = sessionData.getUser();
        boolean isProfileEdited = edited != null;

        List<String> successMessage = userService.getSuccessMessageOnEdit(isProfileEdited);

        model.addAttribute("user", user);
        model.addAttribute("success", successMessage);

        return Path.Template.USER_PROFILE;
    }

    @PostMapping(value = Path.Web.USER_PROFILE)
    public String handleUserProfileUpdate(Model model, @RequestParam Map<String, String> formData) {

        User user = sessionData.getUser();

        List<String> errorMessages = userService.update(user, formData);

        if (errorMessages.size() > 0) {
            model.addAttribute("errors", errorMessages);
            model.addAttribute("user", formData);

            return Path.Template.USER_PROFILE;
        }

        return "redirect:" + Path.Web.USER_PROFILE_SUCCESSFUL_EDIT;
    }

}
