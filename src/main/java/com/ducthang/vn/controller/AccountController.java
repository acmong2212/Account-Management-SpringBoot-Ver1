package com.ducthang.vn.controller;

import com.ducthang.vn.model.Role;
import com.ducthang.vn.model.User;
import com.ducthang.vn.service.IRoleService;
import com.ducthang.vn.service.IUserService;
import com.ducthang.vn.validate.Repeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class AccountController {
    @Autowired
    IUserService userService;

    @Autowired
    IRoleService roleService;

    @Autowired
    Repeat repeat;

    @GetMapping("")
    public ModelAndView show(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("users", userService.findAllUser(PageRequest.of(page, 3)));
        modelAndView.addObject("roles", roleService.findAllRole());
        return modelAndView;
    }

    @GetMapping("/findByFullName")
    public ModelAndView findByName(@RequestParam String findName, @RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("users", userService.findAllByFullNameContaining(findName, PageRequest.of(page, 3)));
        return modelAndView;
    }

//    @GetMapping("")
//    public ModelAndView show() {
//        ModelAndView modelAndView = new ModelAndView("home");
//        modelAndView.addObject("users", userService.findAllUser());
//        modelAndView.addObject("roles", roleService.findAllRole());
//        return modelAndView;
//    }

    @GetMapping("/create")
    public ModelAndView showCreate(){
        ModelAndView modelAndView = new ModelAndView("createAccount");
        return modelAndView;
    }

    @ModelAttribute("user")
    public User user(){
        return new User();
    }

    @ModelAttribute("roles")
    public List<Role> roles(){
        return roleService.findAllRole();
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute(value = "user") User user, BindingResult bindingResult, @RequestParam long idRole, @RequestParam MultipartFile upImg){
        repeat.validate(user, bindingResult);

        if (bindingResult.hasFieldErrors()){
            return "createAccount";
        }

        Role role = new Role();
        role.setIdRole(idRole);
        user.setRole(role);

        String nameFile = upImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File("D:\\Module 4\\Account-Management-SpringBoot\\src\\main\\resources\\static\\image\\" + nameFile));
            user.setAvatar("/image/"+nameFile);
            userService.save(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        if (userService.findById(id).getAvatar()!=null){
            File file = new File("D:\\Module 4\\Account-Management-SpringBoot\\src\\main\\resources\\static\\" +userService.findById(id).getAvatar());
            file.delete();
        }
        userService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public ModelAndView showEdit(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView("/editAccount");
        modelAndView.addObject("user", userService.findById(id));
        modelAndView.addObject("roles", roleService.findAllRole());
        return modelAndView;
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute(value = "user") User user, BindingResult bindingResult, @RequestParam long idRole , @RequestParam MultipartFile upImg) {
        repeat.validate(user, bindingResult);

        if (bindingResult.hasFieldErrors()){
            return "editAccount";
        }

        Role role = new Role();
        role.setIdRole(idRole);
        user.setRole(role);

        if (upImg.getSize() != 0) {

            String deleteImage = "D:\\Module 4\\Account-Management-SpringBoot\\src\\main\\resources\\static\\" + user.getAvatar();
            File file = new File(deleteImage);
            file.delete();
            try {
                String imgFile = upImg.getOriginalFilename();
                FileCopyUtils.copy(upImg.getBytes(), new File("D:\\Module 4\\Account-Management-SpringBoot\\src\\main\\resources\\static\\image\\" + imgFile));
                user.setAvatar("/image/" + imgFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        userService.save(user);
        return "redirect:/";
    }


}
