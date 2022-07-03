package com.restaurant.course.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "menu_item")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menu_item", nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false, columnDefinition = "text", name = "discription")
    private String description;
    @ManyToOne
    @JoinColumn(name = "fk_category")
    private Category category;

    @OneToMany(mappedBy = "menuItem")
    private Set<Basket> baskets;
}
