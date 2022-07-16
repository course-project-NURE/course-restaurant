package com.restaurant.course.repository;

import com.restaurant.course.entity.Staff;
import com.restaurant.course.entity.en.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
    @Query("Select s from Staff s join LoginInfo l on s.loginInfo.id = l.id where l.email = :email")
    Optional<Staff> findByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query("Delete from Staff s where s.loginInfo.id = (Select l.id from LoginInfo l where l.email = :email)")
    void deleteByEmail(@Param("email") String email);

    @Query("Select s from Staff s join LoginInfo l on s.loginInfo.id = l.id join RoleEntity r on r.id = l.role.id where r.title = :role")
    List<Staff> findByRole(Role role);

}
