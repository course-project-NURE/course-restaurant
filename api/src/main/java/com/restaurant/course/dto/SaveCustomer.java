package com.restaurant.course.dto;

import com.restaurant.course.entity.Customer;
import com.restaurant.course.entity.Role;
import com.restaurant.course.entity.Staff;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class SaveCustomer {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String lastname;
    private String phone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaveCustomer that = (SaveCustomer) o;
        return getEmail().equals(that.getEmail()) &&
                getPassword().equals(that.getPassword()) &&
                getName().equals(that.getName()) &&
                getSurname().equals(that.getSurname()) &&
                getLastname().equals(that.getLastname()) &&
                getPhone().equals(that.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getEmail(),
                getPassword(),
                getName(),
                getSurname(),
                getLastname(),
                getPhone());
    }

    public Customer toCustomer(){
        Customer customer = new Customer();

        customer.setName(this.name);
        customer.setSurname(this.surname);
        customer.setLastname(this.lastname);
        customer.setPhone(this.phone);

        return customer;
    }
}
