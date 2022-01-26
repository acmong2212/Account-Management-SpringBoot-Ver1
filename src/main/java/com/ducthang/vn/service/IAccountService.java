package com.ducthang.vn.service;

import com.ducthang.vn.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAccountService {
    Page<Account> findAllAccount(Pageable pageable);
    List<Account> findAllAccount();
    Page<Account> findAllByFullNameContaining(String fullName, Pageable pageable);
    void save(Account user);
    void delete(long id);
    Account findById(long id);
}
