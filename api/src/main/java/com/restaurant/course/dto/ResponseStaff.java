package com.restaurant.course.dto;

import com.restaurant.course.entity.Role;
import com.restaurant.course.entity.Staff;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class ResponseStaff {
    private Integer id;
    private String email;
    private String name;
    private String surname;
    private String lastname;
    private String phone;
    private Integer salary;
    private Role role;

    public ResponseStaff(Staff staff){
        this.setFromStaff(staff);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseStaff that = (ResponseStaff) o;
        return getId().equals(that.getId()) &&
                getEmail().equals(that.getEmail()) &&
                getName().equals(that.getName()) &&
                getSurname().equals(that.getSurname()) &&
                getLastname().equals(that.getLastname()) &&
                getPhone().equals(that.getPhone()) &&
                getSalary().equals(that.getSalary()) &&
                getRole() == that.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getEmail(), getName(), getSurname(), getLastname(), getPhone(), getSalary(), getRole());
    }


    public void setFromStaff(Staff staff){
        this.id = staff.getId();
        this.email = staff.getLoginInfo().getEmail();
        this.name = staff.getName();
        this.surname = staff.getSurname();
        this.lastname = staff.getLastname();
        this.phone = staff.getPhone();
        this.salary = staff.getSalary();
        this.role = staff.getLoginInfo().getRole().getTitle();
    }
}
