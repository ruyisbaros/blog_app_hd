package com.ahmet.blog_app_hd.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CategoryCreateUpdateRequest {

    @NotEmpty(message = "Title value should not be null")
    private String title;
    private String description;
}
