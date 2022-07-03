package com.restaurant.course.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_delivery", nullable = false)
    private Integer id;

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false, columnDefinition = "text")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "fk_order")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "fk_address")
    private Address address;
}
