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
    private String email;

    @NotEmpty(message = "user password should not be null")
    private String password;


    private String about;

    @NotEmpty(message = "Roles should not be null")
    private List<Role> roles;

    private String imageId;
}
