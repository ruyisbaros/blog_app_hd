package com.ahmet.blog_app_hd.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CommentDto {

    @NotEmpty(message = "Content should not be null")
    private String content;
    private Long userId;
    private Long postId;
}
