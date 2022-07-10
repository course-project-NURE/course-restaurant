package com.restaurant.course.entity;

import com.restaurant.course.entity.en.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Category title;

    @OneToMany(mappedBy = "category")
    private List<MenuItem> menuItems;
}
