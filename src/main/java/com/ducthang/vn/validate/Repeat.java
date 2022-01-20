package com.ducthang.vn.validate;

import com.ducthang.vn.model.User;
import com.ducthang.vn.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class Repeat implements Validator {
    @Autowired
    IUserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        List<User> userList = userService.findAllUser();
        for (User u : userList) {
            if (u.getNameUser().equals(user.getNameUser()) && user.getIdUser() != u.getIdUser()) {
                errors.rejectValue("nameUser", "", "Ten^ nay` da~ ton^` tai.");
                return;
            }

            if (u.getEmail().equals(user.getEmail()) && user.getIdUser() != u.getIdUser()) {
                errors.rejectValue("email", "", "Email nay` da~ ton^` tai.");
                return;
            }
        }
    }
}
