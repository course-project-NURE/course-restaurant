package com.restaurant.course.repository;

import com.restaurant.course.entity.CustomerHasAddress;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerHasAddressRepository extends JpaRepository<CustomerHasAddress, Integer> {

}
