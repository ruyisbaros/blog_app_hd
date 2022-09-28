package com.ahmet.blog_app_hd.DTO;

import com.ahmet.blog_app_hd.entities.Image;
import com.ahmet.blog_app_hd.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String  email;
    private String  jwtToken;
    private Image profileImage;
    private List<Role> role;

}
