package com.ahmet.blog_app_hd.controllers;

import com.ahmet.blog_app_hd.DTO.ApiResponse;
import com.ahmet.blog_app_hd.DTO.PostDto;
import com.ahmet.blog_app_hd.entities.Post;
import com.ahmet.blog_app_hd.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@AllArgsConstructor
public class PostController {

    private PostService postService;

    @GetMapping("/all_paginated")
    public Page<Post> getByPostsPaginatedSorted(
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "sortField", defaultValue = "id",required = false) String sortField,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir,
            @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword)
    {
        return postService.postsPaginatedSorted(pageSize, pageNo, sortField,sortDir,keyword);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody PostDto request) {
        Post created = postService.create(request);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody PostDto request) {
        Post updated = postService.updateCat(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws IOException {
        postService.delete(id);

        return ResponseEntity.ok(new ApiResponse("Post has been deleted", true));
    }

    @GetMapping("/all")
    public List<Post> getAll() {
        return postService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        Post found = postService.getOne(id);

        return ResponseEntity.ok(found);
    }

    @GetMapping("/category/{categoryId}")
    public List<Post> getPostsByCategory(@PathVariable Long categoryId) {
        return postService.getPostsByCategory(categoryId);
    }

    @GetMapping("/user/{userId}")
    public List<Post> getPostsByUser(@PathVariable Long userId) {
        return postService.getPostsByUser(userId);
    }
}
