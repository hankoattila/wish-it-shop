package com.codecool.wishit.service;

import com.codecool.wishit.model.Review;
import com.codecool.wishit.utils.Path;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ReviewService {

    public List<Review> getReviewsByProductId(Integer productId) {

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response =
                    restTemplate.getForEntity(Path.MicroServices.REVIEW_SERVICE + "/" + productId, String.class);
            JacksonJsonParser jacksonJsonParser = new JacksonJsonParser();
            Object responseValue = jacksonJsonParser.parseMap(response.getBody()).get("reviewlist");
            ObjectMapper mapper = new ObjectMapper();
            Review[] reviews = mapper.convertValue(responseValue, Review[].class);
            return Arrays.asList(reviews);
        } catch (ResourceAccessException e) {
            System.out.println("Review Service is unavailable: " + e);
            return null;
        }
    }

}
