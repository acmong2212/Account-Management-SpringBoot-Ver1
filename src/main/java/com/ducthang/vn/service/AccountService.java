package com.ducthang.vn.service;

import com.ducthang.vn.model.Account;
import com.ducthang.vn.repository.IAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {
    @Autowired
    IAccountRepo accountRepo;

    @Override
    public Page<Account> findAllAccount(Pageable pageable) {
        return accountRepo.findAll(pageable);
    }

    @Override
    public List<Account> findAllAccount() {
        return (List<Account>) accountRepo.findAll();
    }

    @Override
    public Page<Account> findAllByFullNameContaining(String fullName, Pageable pageable) {
        return accountRepo.findAllByFullNameContaining(fullName, pageable);
    }

    @Override
    public void save(Account user) {
        accountRepo.save(user);
    }

    @Override
    public void delete(long id) {
        accountRepo.deleteById(id);
    }

    @Override
    public Account findById(long id) {
        return accountRepo.findById(id).get();
    }
}
