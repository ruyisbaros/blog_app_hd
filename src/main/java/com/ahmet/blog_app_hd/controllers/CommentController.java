package com.ahmet.blog_app_hd.controllers;

import com.ahmet.blog_app_hd.DTO.CommentDto;
import com.ahmet.blog_app_hd.entities.Comment;
import com.ahmet.blog_app_hd.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@AllArgsConstructor
public class CommentController {

    private CommentService commentService;

    @GetMapping("/post/{postId}")
    public List<Comment> getCommentsByPost(@PathVariable Long postId){
        return commentService.getCommentsByPost(postId);
    }

    @GetMapping("/{id}")
    public Comment getComment(@PathVariable Long id){
        return commentService.getOne(id);
    }

    @PostMapping("/create")
    public Comment createComment(@Valid @RequestBody CommentDto request){
        return commentService.createCom(request);
    }

    @PutMapping("/update/{id}")
    public Comment updateComment(@Valid @RequestBody CommentDto request,@PathVariable Long id){
        return commentService.updateCom(request,id);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id){
        commentService.deleteCom(id);
    }

}
