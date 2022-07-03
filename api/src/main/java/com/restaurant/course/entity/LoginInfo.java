package com.restaurant.course.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "login_info")
@Getter
@Setter
@NoArgsConstructor
public class LoginInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_login_info", nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, columnDefinition = "char")
    private String password;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_role", columnDefinition = "tinyint")
    private RoleEntity role;


    @OneToMany(mappedBy = "loginInfo")
    private List<Customer> customers;

    @OneToMany(mappedBy = "loginInfo")
    private List<Staff> staff;
}
