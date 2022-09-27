package com.ahmet.blog_app_hd.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PostCreateUpdateRequest {
    @NotEmpty(message = "Title value should not be null")
    private String title;
    @NotEmpty(message = "Content value should not be null")
    private String content;
}
