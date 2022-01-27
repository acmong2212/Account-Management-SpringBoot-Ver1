package com.ducthang.vn.controller;

import com.ducthang.vn.model.Categories;
import com.ducthang.vn.model.Account;
import com.ducthang.vn.service.ICategoriesService;
import com.ducthang.vn.service.IAccountService;
import com.ducthang.vn.validate.Repeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AccountController {
    @Autowired
    IAccountService accountService;

    @Autowired
    ICategoriesService categoriesService;

    @Autowired
    Repeat repeat;

    @GetMapping("")
    public ModelAndView show(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("viewAdmin");
        modelAndView.addObject("accounts", accountService.findAllAccount(PageRequest.of(page, 3)));
        modelAndView.addObject("categories", categoriesService.findAllCategories());
        return modelAndView;
    }

    @GetMapping("/user")
    public ModelAndView user() {
        ModelAndView modelAndView = new ModelAndView("viewUser");
        return modelAndView;
    }

    @GetMapping("/admin")
    public ModelAndView admin(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("viewAdmin");
        modelAndView.addObject("accounts", accountService.findAllAccount(PageRequest.of(page, 3)));
        modelAndView.addObject("categories", categoriesService.findAllCategories());
        return modelAndView;
    }

    @GetMapping("/findByFullName")
    public ModelAndView findByName(@RequestParam String findName, @RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("viewAdmin");
        modelAndView.addObject("categories", accountService.findAllByFullNameContaining(findName, PageRequest.of(page, 3)));
        return modelAndView;
    }

//    @GetMapping("")
//    public ModelAndView show() {
//        ModelAndView modelAndView = new ModelAndView("home");
//        modelAndView.addObject("users", userService.findAllUser());
//        modelAndView.addObject("categories", roleService.findAllRole());
//        return modelAndView;
//    }

    @GetMapping("/create")
    public ModelAndView showCreate(){
        ModelAndView modelAndView = new ModelAndView("createAccount");
        return modelAndView;
    }

    @ModelAttribute("account")
    public Account account(){
        return new Account();
    }

    @ModelAttribute("categories")
    public List<Categories> categories(){
        return categoriesService.findAllCategories();
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute(value = "account") Account account, BindingResult bindingResult, @RequestParam long idCategories, @RequestParam MultipartFile upImg){
        repeat.validate(account, bindingResult);

        if (bindingResult.hasFieldErrors()){
            return "createAccount";
        }

        Categories categories = new Categories();
        categories.setIdCategories(idCategories);
        account.setCategories(categories);

        String nameFile = upImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File("D:\\Module 4\\Account-Management-SpringBoot\\src\\main\\resources\\static\\image\\" + nameFile));
            account.setAvatar("/image/"+nameFile);
            accountService.save(account);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        if (accountService.findById(id).getAvatar()!=null){
            File file = new File("D:\\Module 4\\Account-Management-SpringBoot\\src\\main\\resources\\static\\" + accountService.findById(id).getAvatar());
            file.delete();
        }
        accountService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/edit")
    public ModelAndView showEdit(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView("/editAccount");
        modelAndView.addObject("account", accountService.findById(id));
        modelAndView.addObject("categories", categoriesService.findAllCategories());
        return modelAndView;
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute(value = "account") Account account, BindingResult bindingResult, @RequestParam long idCategories, @RequestParam MultipartFile upImg) {
        repeat.validate(account, bindingResult);

        if (bindingResult.hasFieldErrors()){
            return "editAccount";
        }

        Categories categories = new Categories();
        categories.setIdCategories(idCategories);
        account.setCategories(categories);

        if (upImg.getSize() != 0) {

            String deleteImage = "D:\\Module 4\\Account-Management-SpringBoot\\src\\main\\resources\\static\\" + account.getAvatar();
            File file = new File(deleteImage);
            file.delete();
            try {
                String imgFile = upImg.getOriginalFilename();
                FileCopyUtils.copy(upImg.getBytes(), new File("D:\\Module 4\\Account-Management-SpringBoot\\src\\main\\resources\\static\\image\\" + imgFile));
                account.setAvatar("/image/" + imgFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        accountService.save(account);
        return "redirect:/admin";
    }
}
