package com.restaurant.course.repository;

import com.restaurant.course.entity.CustomerHasAddress;
import com.restaurant.course.entity.CustomerHasAddressId;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CustomerHasAddressRepository extends JpaRepository<CustomerHasAddress, CustomerHasAddressId> {

}
