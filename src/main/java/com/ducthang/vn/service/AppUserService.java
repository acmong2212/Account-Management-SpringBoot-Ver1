package com.ducthang.vn.service;

import com.ducthang.vn.model.AppUser;
import com.ducthang.vn.repository.IAppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AppUserService implements IAppUserService{
    @Autowired
    IAppUserRepo appUserRepo;

    @Override
    public List<AppUser> findAllAppUser() {
        return (List<AppUser>) appUserRepo.findAll();
    }

    @Override
    public void save(AppUser appUser) {
        appUserRepo.save(appUser);
    }

    @Override
    public void delete(Long id) {
        appUserRepo.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepo.findByUserName(username);
        return new User(appUser.getUserName(),appUser.getPassword(),appUser.getRoles());
    }
}
