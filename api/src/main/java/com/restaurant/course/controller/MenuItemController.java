package com.restaurant.course.controller;

import com.restaurant.course.dto.*;
import com.restaurant.course.entity.en.Category;
import com.restaurant.course.service.MenuItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseMenuItem getMenuItemById(@PathVariable Integer id){
        return menuItemService.getMenuItemById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<ResponseMenuItem> getAllMenuItems(){
        return menuItemService.getAllMenuItems();
    }

    @GetMapping("/category/{category}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ResponseMenuItem> getAllMenuItemsByCategory(@PathVariable String category){
        return menuItemService.getAllMenuItemsByCategory(Category.valueOf(category));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseMenuItem updateMenuItem(@PathVariable Integer id, @RequestBody SaveMenuItem item){
        return menuItemService.updateMenuItem(id, item);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteMenuItemById(@PathVariable Integer id){
        menuItemService.deleteMenuItemById(id);
        return ResponseEntity.noContent().build();
    }
}
