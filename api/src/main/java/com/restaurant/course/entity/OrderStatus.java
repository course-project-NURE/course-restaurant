package com.restaurant.course.entity;

import com.restaurant.course.entity.en.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "order_status")
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_status", nullable = false, columnDefinition = "tinyint")
    private Integer id;

    @Column(name = "title", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status title;

    @OneToMany(mappedBy = "orderStatus")
    private List<Order> orders;
}
