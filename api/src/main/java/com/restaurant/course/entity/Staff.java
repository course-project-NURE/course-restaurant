package com.restaurant.course.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_staff", nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, columnDefinition="text")
    private String lastname;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private Integer salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_login_info")
    private LoginInfo loginInfo;

    @ManyToMany
    @JoinTable(
            name = "staff_has_order",
            joinColumns = {@JoinColumn(name = "Staff_id")},
            inverseJoinColumns = {@JoinColumn(name = "Order_id")}
    )
    private Set<Order> orders;
}
