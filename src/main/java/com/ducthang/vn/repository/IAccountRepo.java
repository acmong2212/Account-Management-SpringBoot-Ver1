package com.ducthang.vn.repository;

import com.ducthang.vn.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IAccountRepo extends PagingAndSortingRepository<Account, Long> {
    //Search khi phân trang
    Page<Account> findAllByFullNameContaining(String fullName, Pageable pageable);

    //Search khi chưa phân trang
//    @Query(value = "select u from User u where u.fullName like concat('%',:fullName,'%')")
//    ArrayList<User> findAllByFullName(@Param("fullName") String fullName);
}
