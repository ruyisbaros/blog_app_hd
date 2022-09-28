package com.ahmet.blog_app_hd.controllers;

import com.ahmet.blog_app_hd.DTO.ApiResponse;
import com.ahmet.blog_app_hd.DTO.CategoryDto;
import com.ahmet.blog_app_hd.entities.Category;
import com.ahmet.blog_app_hd.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody CategoryDto request){
        Category created=categoryService.createNew(request);

        return ResponseEntity.ok(created);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody CategoryDto request){
        Category updated=categoryService.updateCat(id,request);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/all")
    public List<Category> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        Category found = categoryService.fondOne(id);

        return ResponseEntity.ok(found);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCat(@PathVariable long id){
        categoryService.deleteCat(id);

        return ResponseEntity.ok(new ApiResponse("category has been deleted",true));
    }
}
