package com.restaurant.course.dto;

import com.restaurant.course.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class ResponseStaff {
    private String id;
    private String email;
    private String name;
    private String surname;
    private String lastname;
    private String phone;
    private Integer salary;
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseStaff that = (ResponseStaff) o;
        return getEmail().equals(that.getEmail()) && getName().equals(that.getName()) && getSurname().equals(that.getSurname()) && getLastname().equals(that.getLastname()) && getPhone().equals(that.getPhone()) && getSalary().equals(that.getSalary()) && getRole() == that.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getName(), getSurname(), getLastname(), getPhone(), getSalary(), getRole());
    }
}
