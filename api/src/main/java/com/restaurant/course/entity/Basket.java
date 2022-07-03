package com.restaurant.course.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Basket {
    @EmbeddedId
    private BasketId basketId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = MenuItem.class)
    @MapsId("menuItemId")
    @JoinColumn(name = "Menu_item_id")
    private MenuItem menuItem;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Order.class)
    @MapsId("orderId")
    @JoinColumn(name = "Order_id")
    private Order order;


    @Column(nullable = false)
    private Integer quantity;
}
