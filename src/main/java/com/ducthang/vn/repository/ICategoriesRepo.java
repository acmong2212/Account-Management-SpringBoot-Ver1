package com.ducthang.vn.repository;

import com.ducthang.vn.model.Categories;
import org.springframework.data.repository.CrudRepository;

public interface ICategoriesRepo extends CrudRepository<Categories, Long> {
}
