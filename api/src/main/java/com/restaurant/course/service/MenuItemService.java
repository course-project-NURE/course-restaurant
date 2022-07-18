package com.restaurant.course.service;

import com.restaurant.course.dto.ResponseMenuItem;
import com.restaurant.course.dto.SaveMenuItem;
import com.restaurant.course.entity.CategoryEntity;
import com.restaurant.course.entity.en.Category;
import com.restaurant.course.entity.MenuItem;
import com.restaurant.course.exception.CategoryException;
import com.restaurant.course.exception.MenuItemException;
import com.restaurant.course.exception.StaffException;
import com.restaurant.course.repository.CategoryRepository;
import com.restaurant.course.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final CategoryRepository categoryRepository;

    public MenuItemService(
            MenuItemRepository menuItemRepository,
            CategoryRepository categoryRepository
    ) {
        this.menuItemRepository = menuItemRepository;
        this.categoryRepository = categoryRepository;
    }

    public ResponseMenuItem getMenuItemById(Integer id){
        MenuItem menuItem = menuItemRepository.findById(id).orElseThrow(() ->
                MenuItemException.menuItemNotFoundById(id)
        );
        return new ResponseMenuItem(menuItem);
    }

    public List<ResponseMenuItem> getAllMenuItems(){
        List<MenuItem> menuItems = menuItemRepository.findAll();

        List<ResponseMenuItem> response = new ArrayList<>();
        if(!menuItems.isEmpty()){
            for(MenuItem m: menuItems){
                response.add(new ResponseMenuItem(m));
            }
        }
        return response;
    }

    public List<ResponseMenuItem> getAllMenuItemsByCategory(Category category){
        List<MenuItem> menuItems = menuItemRepository.findAllByCategory(category);

        List<ResponseMenuItem> response = new ArrayList<>();
        if(!menuItems.isEmpty()){
            for(MenuItem m: menuItems){
                response.add(new ResponseMenuItem(m));
            }
        }
        return response;
    }

    public ResponseMenuItem saveMenuItem(SaveMenuItem saveMenuItem){
        MenuItem menuItem = saveMenuItem.toMenuItem();

        CategoryEntity category = categoryRepository.findByTitle(saveMenuItem.getCategory()).orElseThrow(
                () -> CategoryException.categoryNotFoundByTitle(saveMenuItem.getCategory().name())
        );

        menuItem.setCategory(category);

        return new ResponseMenuItem(menuItemRepository.save(menuItem));
    }

    public ResponseMenuItem updateMenuItem(Integer id,SaveMenuItem saveMenuItem){
        MenuItem menuItem = menuItemRepository.findById(id).orElseThrow(
                () -> MenuItemException.menuItemNotFoundById(id)
        );

        menuItem.setTitle(saveMenuItem.getTitle());
        menuItem.setDescription(saveMenuItem.getDescription());
        menuItem.setPrice(saveMenuItem.getPrice());

        CategoryEntity category = categoryRepository.findByTitle(saveMenuItem.getCategory()).orElseThrow(
                () -> CategoryException.categoryNotFoundByTitle(saveMenuItem.getCategory().name())
        );

        menuItem.setCategory(category);

        return new ResponseMenuItem(menuItemRepository.save(menuItem));
    }

    public void deleteMenuItemById(Integer id){
        Optional<MenuItem> menuItem = menuItemRepository.findById(id);
        if(menuItem.isPresent()){
            menuItemRepository.deleteById(id);
        }else{
            throw StaffException.staffNotFoundById(id);
        }
    }
}
