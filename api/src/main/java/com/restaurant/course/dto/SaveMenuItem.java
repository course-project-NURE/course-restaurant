package com.restaurant.course.dto;

import com.restaurant.course.entity.en.Category;
import com.restaurant.course.entity.MenuItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class SaveMenuItem {
    private String title;
    private Float price;
    private String description;
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaveMenuItem that = (SaveMenuItem) o;
        return getTitle().equals(that.getTitle()) &&
                getPrice().equals(that.getPrice()) &&
                getDescription().equals(that.getDescription()) &&
                getCategory() == that.getCategory();
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getTitle(),
                getPrice(),
                getDescription(),
                getCategory());
    }

    public MenuItem toMenuItem(){
        return new MenuItem(
                this.title,
                this.price,
                this.description
        );
    }
}
