package com.ducthang.vn.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idUser;

    @NotEmpty(message = "Khong^ duoc de? trong^'")
    private String nameUser;

    @NotEmpty(message = "Khong^ duoc de? trong^'")
    private String passWord;

    @NotEmpty(message = "Khong^ duoc de? trong^'")
    @Pattern(regexp = "^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶ\" +\n" +
            "\"ẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợ\" +\n" +
            "\"ụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]+$", message = "Ho va ten khong bao gom` so va ki tu dac biet")
    private String fullName;

    @NotEmpty(message = "Khong^ duoc de? trong^'")
    private String phoneNumber;

    @NotEmpty(message = "Khong^ duoc de? trong^'")
    @Email
    private String email;

    @Column(length=5000)
    private String avatar;

    @ManyToOne
    private Role role;
}
