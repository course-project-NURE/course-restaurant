package com.restaurant.course.repository;

import com.restaurant.course.entity.LoginInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginInfoRepository extends JpaRepository<LoginInfo, Integer> {
    void deleteByEmail(String email);
}
