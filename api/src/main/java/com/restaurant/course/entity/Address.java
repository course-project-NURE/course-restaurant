package com.restaurant.course.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address", nullable = false)
    private Integer id;

    @Column(nullable = false, columnDefinition = "text")
    private String street;

    @Column(nullable = false, name = "hous_n")
    private Integer house;

    @Column(nullable = false)
    private String flat;

    @OneToMany(mappedBy = "address")
    private Set<CustomerHasAddress> CustomerHasAddress;

    @OneToMany(mappedBy = "address")
    private List<Delivery> deliveries;
}
