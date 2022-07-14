package com.restaurant.course.dto;

import com.restaurant.course.entity.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class SaveAddress {
    private String title;
    private String street;
    private Integer house;
    private String flat;

    public Address toAddress(){
        Address address = new Address();

        address.setStreet(this.street);
        address.setFlat(this.flat);
        address.setHouse(this.house);

        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaveAddress that = (SaveAddress) o;
        return Objects.equals(title, that.title) && Objects.equals(street, that.street) && Objects.equals(house, that.house) && Objects.equals(flat, that.flat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, street, house, flat);
    }
}

