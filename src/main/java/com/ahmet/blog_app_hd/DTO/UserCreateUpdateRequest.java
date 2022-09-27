package com.ahmet.blog_app_hd.DTO;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserCreateUpdateRequest {

    @NotEmpty(message = "user name should not be null")
    private String name;
    @Email(message = "Email address is not valid")
    @NotEmpty(message = "user email should not be null")
    //@UniqueElements(message = "User with this email already exist")
    private String email;
    @NotEmpty(message = "user password should not be null")
    private String password;
    @NotEmpty(message = "user about should not be null")
    private String about;
    private String roleName;
    private String imageId;
}
