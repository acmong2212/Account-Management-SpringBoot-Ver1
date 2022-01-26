package com.ducthang.vn.service;

import com.ducthang.vn.model.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IAppUserService extends UserDetailsService {
    List<AppUser> findAllAppUser();
    void save(AppUser appUser);
    void delete(Long id);
}
