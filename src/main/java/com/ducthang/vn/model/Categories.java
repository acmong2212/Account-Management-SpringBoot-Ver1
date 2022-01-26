package com.ducthang.vn.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Categories {
    @Id
    private Long idCategories;
    private String nameCategories;
}
