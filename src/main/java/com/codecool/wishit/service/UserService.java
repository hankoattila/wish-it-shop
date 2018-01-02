package com.codecool.wishit.service;

import com.codecool.wishit.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    public List<String> validateRegistrationInput(Map<String, String> formData) {
        // TODO: send formData (originated from register.html form) to user-microservice for validation
        // TODO: expected return is a list of errors, if 'errors' key is not found in
        // TODO: response JSON (or maybe empty list) then successful registration
        return null;
    }

    public User validateLoginCredentials(Map<String, String> inputData) {

        return null;
    }

    public List<String> getInvalidLoginCredsErrorMessage() {
        return new ArrayList<>(Collections.singletonList("Invalid credentials."));
    }

    public List<String> getSuccessMessageOnEdit(boolean isProfileEdited) {
        return isProfileEdited ? new ArrayList<>(Collections.singletonList("Profile successfully edited.")) : null;
    }

    public List<String> update(User user, Map<String, String> formData) {
        // TODO: communicate with user-microservice and send user id and updated user data (found in formData)
        // TODO: return list of errors if invalid input
        return null;
    }
}
