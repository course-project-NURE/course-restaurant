package com.restaurant.course.dto;

import com.restaurant.course.entity.CustomerHasAddress;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class ResponseAddress {
    private Integer id;
    private String title;
    private String street;
    private Integer house;
    private String flat;


    public ResponseAddress(CustomerHasAddress hasAddress){this.setFromCustomerHasAddress(hasAddress);}


    public void setFromCustomerHasAddress(CustomerHasAddress hasAddress){
        this.id = hasAddress.getAddress().getId();
        this.title = hasAddress.getTitle();
        this.street = hasAddress.getAddress().getStreet();
        this.flat = hasAddress.getAddress().getFlat();
        this.house = hasAddress.getAddress().getHouse();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseAddress that = (ResponseAddress) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(street, that.street) && Objects.equals(house, that.house) && Objects.equals(flat, that.flat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, street, house, flat);
    }
}
