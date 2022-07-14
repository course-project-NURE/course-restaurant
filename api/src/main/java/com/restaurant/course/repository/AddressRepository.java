package com.restaurant.course.repository;

import com.restaurant.course.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("Select a from Address a where a.flat = :flat and a.street = :street and a.house = :house")
    Address findAddress(@Param("street") String street, @Param("house") Integer house,@Param("flat") String flat);
}
