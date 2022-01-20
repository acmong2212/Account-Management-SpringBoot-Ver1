package com.ducthang.vn.service;

import com.ducthang.vn.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    public Page<User> findAllUser(Pageable pageable);
    public List<User> findAllUser();
    public List<User> findAllByFullName(String fullName);
    public void save(User user);
    public void delete(long id);
    public User findById(long id);
}
