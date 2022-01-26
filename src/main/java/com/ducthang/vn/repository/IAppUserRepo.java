package com.ducthang.vn.repository;

import com.ducthang.vn.model.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface IAppUserRepo extends CrudRepository<AppUser, Long> {
    AppUser findByUserName(String userName);
}
