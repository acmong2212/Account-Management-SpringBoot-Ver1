package com.ducthang.vn.repository;

import com.ducthang.vn.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepo extends PagingAndSortingRepository<User, Long> {
    //Search khi phân trang
    Page<User> findAllByFullNameContaining(String fullName, Pageable pageable);

    //Search khi chưa phân trang
//    @Query(value = "select u from User u where u.fullName like concat('%',:fullName,'%')")
//    ArrayList<User> findAllByFullName(@Param("fullName") String fullName);
}
