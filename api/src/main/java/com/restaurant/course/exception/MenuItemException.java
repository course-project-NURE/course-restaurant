package com.restaurant.course.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MenuItemException {
    private static final String NO_MENU_ITEMS = "There aren't any staff in database";


    public static ResponseStatusException menuItemNotFoundById(Integer id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu item with id " + id + " was not found");
    }

    public static ResponseStatusException NoOneMenuItemsInDb() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, NO_MENU_ITEMS);
    }
}
