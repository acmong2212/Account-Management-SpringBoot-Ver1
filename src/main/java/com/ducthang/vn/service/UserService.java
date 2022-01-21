package com.ducthang.vn.service;

import com.ducthang.vn.model.User;
import com.ducthang.vn.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    UserRepo userRepo;

    @Override
    public Page<User> findAllUser(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    @Override
    public List<User> findAllUser() {
        return (List<User>) userRepo.findAll();
    }

    @Override
    public Page<User> findAllByFullNameContaining(String fullName, Pageable pageable) {
        return userRepo.findAllByFullNameContaining(fullName, pageable);
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public void delete(long id) {
        userRepo.deleteById(id);
    }

    @Override
    public User findById(long id) {
        return userRepo.findById(id).get();
    }
}
