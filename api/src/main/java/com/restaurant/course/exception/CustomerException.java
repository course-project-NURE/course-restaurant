package com.restaurant.course.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomerException {
    private static final String NO_CUSTOMER = "There aren't any customer in database";

    public static ResponseStatusException customerNotFoundById(Integer id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id " + id + " was not found");
    }

    public static ResponseStatusException customerNotFoundByEmail(String email) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with email " + email + " was not found");
    }

    public static ResponseStatusException NoOneCustomerInDb() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, NO_CUSTOMER);
    }
}
