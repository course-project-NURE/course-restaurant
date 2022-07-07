package com.restaurant.course.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RoleException {
    public static ResponseStatusException roleNotFoundByTitle(String title) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Role with a title " + title + " was not found");
    }
}
