package com.restaurant.course.repository;

import com.restaurant.course.entity.en.Category;
import com.restaurant.course.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

    @Query("Select m from MenuItem m join CategoryEntity c on m.category.id = c.id where c.title = :category")
    List<MenuItem> findAllByCategory(Category category);
}
