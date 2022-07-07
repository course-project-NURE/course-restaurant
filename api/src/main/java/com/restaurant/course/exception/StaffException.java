package com.restaurant.course.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class StaffException{
    private static final String NO_STAFF = "There aren't any staff in database";

    public static ResponseStatusException staffNotFoundById(Integer id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff with id " + id + " was not found");
    }

    public static ResponseStatusException staffNotFoundByEmail(String email) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff with email " + email + " was not found");
    }

    public static ResponseStatusException NoOneStaffInDb() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, NO_STAFF);
    }

}
