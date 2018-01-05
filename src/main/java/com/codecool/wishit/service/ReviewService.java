package com.codecool.wishit.service;

import com.codecool.wishit.model.ProductReview;
import com.codecool.wishit.utils.Path;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ReviewService {

    public List<ProductReview> getReviewsByProductId(Integer productId) {

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response =
                    restTemplate.getForEntity(Path.MicroServices.REVIEW_SERVICE + "/" + productId, String.class);
            JacksonJsonParser jacksonJsonParser = new JacksonJsonParser();
            Object responseValue = jacksonJsonParser.parseMap(response.getBody()).get("reviewlist");
            ObjectMapper mapper = new ObjectMapper();
            ProductReview[] reviews = mapper.convertValue(responseValue, ProductReview[].class);
            return Arrays.asList(reviews);
        } catch (ResourceAccessException e) {
            System.out.println("Review Service is unavailable: " + e);
            return null;
        }
    }

    public void sendProductReview(String text, Integer star, Integer userId, Integer productId) { ;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            RestTemplate restTemplate = new RestTemplate();

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("productid", Integer.toString(productId));
            params.add("text", text);
            params.add("star", Integer.toString(star));
            params.add("userid", Integer.toString(userId));

            HttpEntity<?> request = new HttpEntity<>(params, headers);
            restTemplate.postForLocation(Path.MicroServices.REVIEW_SERVICE + "/product-review", request);
        } catch (ResourceAccessException e) {
            System.out.println("Review Service is unavailable: " + e);
        }
    }
}
