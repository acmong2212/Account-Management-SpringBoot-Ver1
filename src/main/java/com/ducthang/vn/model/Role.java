package com.ducthang.vn.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Role {
    @Id
    private Long idRole;
    private String nameRole;
}
