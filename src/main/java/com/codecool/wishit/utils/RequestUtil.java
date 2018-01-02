package com.codecool.wishit.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RequestUtil {

    public Map<String, String> collectRegistrationData(Map<String, String> form) {
        Map<String, String> registrationData = new HashMap<>();
        registrationData.put("username", form.get("username"));
        registrationData.put("email", form.get("email"));
        registrationData.put("password1", form.get("password1"));
        registrationData.put("password2", form.get("password2"));

        return registrationData;
    }

    public Map<String, String> collectLoginData(Map<String, String> form) {
        Map<String, String> loginData = new HashMap<>();
        loginData.put("username", form.get("username"));
        loginData.put("password", form.get("password"));

        return loginData;
    }

}
