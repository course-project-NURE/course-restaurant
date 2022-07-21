package com.restaurant.course.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "menu_item")
@NoArgsConstructor
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menu_item", nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false, columnDefinition = "text", name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "fk_category")
    private CategoryEntity category;

    @OneToMany(mappedBy = "menuItem")
    private Set<Basket> baskets;

    public MenuItem(String title, Float price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }
}
