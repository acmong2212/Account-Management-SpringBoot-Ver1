package com.ducthang.vn.service;

import com.ducthang.vn.model.Role;
import com.ducthang.vn.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService{
    @Autowired
    RoleRepo roleRepo;

    @Override
    public List<Role> findAllRole() {
        return (List<Role>) roleRepo.findAll();
    }
}
