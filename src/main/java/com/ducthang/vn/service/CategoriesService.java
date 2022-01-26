package com.ducthang.vn.service;

import com.ducthang.vn.model.Categories;
import com.ducthang.vn.repository.ICategoriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService implements ICategoriesService {
    @Autowired
    ICategoriesRepo categoriesRepo;

    @Override
    public List<Categories> findAllCategories() {
        return (List<Categories>) categoriesRepo.findAll();
    }
}
