package com.ahmet.blog_app_hd.DTO;

import lombok.Data;

@Data
public class UserCreateUpdateRequest {

    private String name;
    private String email;
    private String password;
    private String about;
    //private String roleName;
}
