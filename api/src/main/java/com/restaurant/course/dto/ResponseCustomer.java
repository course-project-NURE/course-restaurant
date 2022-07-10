package com.restaurant.course.dto;

import com.restaurant.course.entity.Customer;
import com.restaurant.course.entity.en.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class ResponseCustomer {
    private Integer id;
    private String email;
    private String name;
    private String surname;
    private String lastname;
    private String phone;
    private Role role;

    public ResponseCustomer(Customer customer){this.setFromCustomer(customer);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseCustomer that = (ResponseCustomer) o;
        return getId().equals(that.getId()) &&
                getEmail().equals(that.getEmail()) &&
                getName().equals(that.getName()) &&
                getSurname().equals(that.getSurname()) &&
                getLastname().equals(that.getLastname()) &&
                getPhone().equals(that.getPhone()) &&
                getRole() == that.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getEmail(),
                getName(),
                getSurname(),
                getLastname(),
                getPhone(),
                getRole());
    }

    public void setFromCustomer(Customer customer){
        this.id = customer.getId();
        this.email = customer.getLoginInfo().getEmail();
        this.name = customer.getName();
        this.surname = customer.getSurname();
        this.lastname = customer.getLastname();
        this.phone = customer.getPhone();
        this.role = customer.getLoginInfo().getRole().getTitle();
    }
}
