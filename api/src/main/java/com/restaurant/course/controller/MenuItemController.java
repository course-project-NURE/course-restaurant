package com.restaurant.course.controller;

import com.restaurant.course.dto.*;
import com.restaurant.course.entity.MenuItem;
import com.restaurant.course.service.MenuItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu-item")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMenuItem saveMenuItem(@RequestBody SaveMenuItem item){
        return menuItemService.saveMenuItem(item);
    }
}
