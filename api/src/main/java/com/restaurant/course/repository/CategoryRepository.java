package com.restaurant.course.repository;

import com.restaurant.course.entity.CategoryEntity;
import com.restaurant.course.entity.en.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    Optional<CategoryEntity> findByTitle(Category title);
}
