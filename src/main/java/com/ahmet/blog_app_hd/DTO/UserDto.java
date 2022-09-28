package com.ahmet.blog_app_hd.DTO;

import com.ahmet.blog_app_hd.entities.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class UserDto {

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
    private List<Role> roles;
    private String imageId;
}
