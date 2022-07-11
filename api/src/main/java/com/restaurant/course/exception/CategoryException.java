package com.restaurant.course.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CategoryException {
    public static ResponseStatusException categoryNotFoundByTitle(String title) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with a title " + title + " was not found");
    }
}
