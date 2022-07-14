package com.restaurant.course.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order", nullable = false)
    private Integer id;

    @Column(nullable = false, columnDefinition = "date")
    private LocalDate date;

    @Column(nullable = false, columnDefinition = "time")
    private LocalTime time;

    @Column(nullable = false, columnDefinition = "text")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_status")
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order")
    private Set<Basket> baskets;

    @ManyToMany(mappedBy = "orders")
    private Set<Staff> staff;

    @OneToMany(mappedBy = "order")
    private List<Delivery> deliveries;
}
