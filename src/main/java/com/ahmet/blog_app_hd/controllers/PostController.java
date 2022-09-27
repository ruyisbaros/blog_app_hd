package com.ahmet.blog_app_hd.controllers;

import com.ahmet.blog_app_hd.DTO.ApiResponse;
import com.ahmet.blog_app_hd.DTO.CategoryCreateUpdateRequest;
import com.ahmet.blog_app_hd.DTO.PostCreateUpdateRequest;
import com.ahmet.blog_app_hd.entities.Category;
import com.ahmet.blog_app_hd.entities.Post;
import com.ahmet.blog_app_hd.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@AllArgsConstructor
public class PostController {

    private PostService postService;

    @PostMapping("create")
    public ResponseEntity<?> create(@Valid @RequestBody PostCreateUpdateRequest request) {
        Post created = postService.create(request);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody PostCreateUpdateRequest request) {
        Post updated = postService.updateCat(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        postService.delete(id);

        return ResponseEntity.ok(new ApiResponse("Post has been deleted",true));
    }

    @GetMapping("/all")
    public List<Post> getAll(){
        return postService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        Post found=postService.getOne(id);

        return ResponseEntity.ok(found);
    }
}
