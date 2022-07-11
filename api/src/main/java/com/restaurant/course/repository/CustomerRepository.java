package com.restaurant.course.repository;

import com.restaurant.course.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    @Query("Select c from Customer c join LoginInfo l on c.loginInfo.id = l.id where l.email = :email")
    Optional<Customer> findByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query("Delete from Customer c where c.loginInfo.id = (Select l.id from LoginInfo l where l.email = :email)")
    void deleteByEmail(@Param("email") String email);
}
