package com.ducthang.vn.service;

import com.ducthang.vn.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    public Page<User> findAllUser(Pageable pageable);
    public List<User> findAllUser();
    Page<User> findAllByFullNameContaining(String fullName, Pageable pageable);
    public void save(User user);
    public void delete(long id);
    public User findById(long id);
}
