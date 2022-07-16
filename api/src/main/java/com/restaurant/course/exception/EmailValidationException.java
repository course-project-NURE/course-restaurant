package com.restaurant.course.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailValidationException {

    public static ResponseStatusException invalidEmail(String email) {
        return new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Email:" + email + "doesn't match");
    }

}
