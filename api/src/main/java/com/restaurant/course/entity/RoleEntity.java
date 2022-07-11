package com.restaurant.course.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role", nullable = false, columnDefinition = "tinyint")
    private Integer id;

    @Column(name = "title", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role title;

    @OneToMany(mappedBy = "role")
    private List<LoginInfo> loginInfos;

    public RoleEntity(Role title) {
        this.title = title;
    }
}
