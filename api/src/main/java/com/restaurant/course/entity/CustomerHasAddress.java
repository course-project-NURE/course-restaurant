package com.restaurant.course.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "customer_has_address")
public class CustomerHasAddress {

    @EmbeddedId
    private CustomerHasAddressId customerHasAddressId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = MenuItem.class)
    @MapsId("customerId")
    @JoinColumn(name = "Customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Order.class)
    @MapsId("addressId")
    @JoinColumn(name = "Address_id")
    private Address address;

    @Column(nullable = false)
    private String title;
}
