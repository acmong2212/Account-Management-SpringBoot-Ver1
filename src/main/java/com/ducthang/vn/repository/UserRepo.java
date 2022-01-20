package com.ducthang.vn.repository;

import com.ducthang.vn.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface UserRepo extends PagingAndSortingRepository<User, Long> {
    //Search
    @Query(value = "select u from User u where u.fullName like concat('%',:fullName,'%')")
    ArrayList<User> findAllByFullName(@Param("fullName") String fullName);
}
