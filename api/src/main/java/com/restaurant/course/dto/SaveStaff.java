package com.restaurant.course.dto;

import com.restaurant.course.entity.LoginInfo;
import com.restaurant.course.entity.Role;
import com.restaurant.course.entity.RoleEntity;
import com.restaurant.course.entity.Staff;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class SaveStaff {
    private String email;
    private String password;
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
        SaveStaff saveStaff = (SaveStaff) o;
        return getEmail().equals(saveStaff.getEmail()) &&
                getPassword().equals(saveStaff.getPassword()) &&
                getName().equals(saveStaff.getName()) &&
                getSurname().equals(saveStaff.getSurname()) &&
                getLastname().equals(saveStaff.getLastname()) &&
                getPhone().equals(saveStaff.getPhone()) &&
                getSalary().equals(saveStaff.getSalary()) &&
                getRole() == saveStaff.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getEmail(),
                getPassword(),
                getName(),
                getSurname(),
                getLastname(),
                getPhone(),
                getSalary(),
                getRole());
    }

    public Staff toStaff(){
        Staff staff = new Staff();

        staff.setName(this.name);
        staff.setSurname(this.surname);
        staff.setLastname(this.lastname);
        staff.setPhone(this.phone);
        staff.setSalary(this.salary);

        return staff;
    }
}
