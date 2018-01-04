package com.codecool.wishit.service;

import com.codecool.wishit.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class UserService {

    private final String LOCAL_URL = "http://192.168.161.149:8080/";
    private final String HEROKU_URL = "http://user-service-wishit.herokuapp.com/";

    public List<String> doRegistration(MultiValueMap<String, String> formData) {

        if (formData == null) {
            return new ArrayList<>();
        }

        final String URI = HEROKU_URL + "registration";

        String response;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<Map> entity = new HttpEntity<>(formData , headers);

        try {
            response = restTemplate.exchange(URI, HttpMethod.POST, entity, String.class).getBody();
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }

        Gson gson = new Gson();
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();

        if (jsonObject.has("error")) {
            return gson.fromJson(jsonObject.get("error"), ArrayList.class);
        }

        return null;
    }

    public User validateLoginCredentials(MultiValueMap<String, String> formData) {

        final String URI = HEROKU_URL + "login";

        String response;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<Map> entity = new HttpEntity<>(formData , headers);

        try {
            response = restTemplate.exchange(URI, HttpMethod.POST, entity, String.class).getBody();
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            return null;
        }

        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();

        if (jsonObject.has("message") && !jsonObject.get("message").getAsString().equals("success")) {
            return null;
        }

        Gson gson = new Gson();
        return gson.fromJson(jsonObject.get("user"), User.class);
    }

    public List<String> getInvalidLoginCredsErrorMessage() {
        return new ArrayList<>(Collections.singletonList("Invalid credentials."));
    }

    public List<String> getSuccessMessageOnEdit(boolean isProfileEdited) {
        return isProfileEdited ? new ArrayList<>(Collections.singletonList("Profile successfully edited.")) : null;
    }

    public Map<String, Object> update(User user, MultiValueMap<String, String> formData) {

        Map<String, Object> res = new HashMap<>();
        res.put("user", null);

        if (formData == null && user == null) {
            res.put("errors", new ArrayList<>());
            return res;
        }

        final String URI = HEROKU_URL + String.format("users/%d", user.getUserId());

        String response;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<Map> entity = new HttpEntity<>(formData , headers);

        try {
            response = restTemplate.exchange(URI, HttpMethod.POST, entity, String.class).getBody();
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            res.put("errors", new ArrayList<>());
            return res;
        }

        Gson gson = new Gson();
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();

        if (!jsonObject.get("message").getAsString().equals("success")) {
            res.put("errors", gson.fromJson(jsonObject.get("message"), ArrayList.class));
            return res;
        }

        res.put("user", gson.fromJson(jsonObject.get("user"), User.class));
        return res;
    }

}
