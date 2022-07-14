package com.restaurant.course.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "customer_has_address")
@NoArgsConstructor
public class CustomerHasAddress {

    @EmbeddedId
    private CustomerHasAddressId customerHasAddressId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
    @MapsId("customerId")
    @JoinColumn(name = "Customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Address.class)
    @MapsId("addressId")
    @JoinColumn(name = "Address_id")
    private Address address;

    @Column(nullable = false)
    private String title;

    public CustomerHasAddress(Customer customer, Address address, String title) {
        this.customer = customer;
        this.address = address;
        this.title = title;

        CustomerHasAddressId customerHasAddressId1 = new CustomerHasAddressId(customer.getId(), address.getId());
    }
}
