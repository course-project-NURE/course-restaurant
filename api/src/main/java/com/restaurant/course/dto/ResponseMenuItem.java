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
public class ResponseMenuItem {
    private Integer id;
    private String title;
    private Float price;
    private String description;
    private Category category;

    public ResponseMenuItem(MenuItem menuItem){
        setFromMenuItem(menuItem);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseMenuItem that = (ResponseMenuItem) o;
        return getId().equals(that.getId()) && getTitle().equals(that.getTitle()) && getPrice().equals(that.getPrice()) && getDescription().equals(that.getDescription()) && getCategory() == that.getCategory();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getPrice(), getDescription(), getCategory());
    }

    public void setFromMenuItem(MenuItem menuItem){
        this.id = menuItem.getId();
        this.title = menuItem.getTitle();
        this.price = menuItem.getPrice();
        this.description = menuItem.getDescription();
        this.category = menuItem.getCategory().getTitle();
    }
}
