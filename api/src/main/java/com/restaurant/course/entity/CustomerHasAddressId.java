package com.restaurant.course.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerHasAddressId implements Serializable {

    private Integer customerId;

    private Integer addressId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerHasAddressId that = (CustomerHasAddressId) o;
        return getCustomerId().equals(that.getCustomerId()) && getAddressId().equals(that.getAddressId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId(), getAddressId());
    }
}
