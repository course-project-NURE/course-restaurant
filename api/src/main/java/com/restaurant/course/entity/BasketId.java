package com.restaurant.course.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketId implements Serializable {

    private Integer menuItemId;

    private Integer orderId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketId basketId = (BasketId) o;
        return getMenuItemId().equals(basketId.getMenuItemId()) && getOrderId().equals(basketId.getOrderId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMenuItemId(), getOrderId());
    }
}
