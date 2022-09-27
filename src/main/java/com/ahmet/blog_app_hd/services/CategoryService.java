package com.ahmet.blog_app_hd.services;

import com.ahmet.blog_app_hd.DTO.CategoryCreateUpdateRequest;
import com.ahmet.blog_app_hd.entities.Category;
import com.ahmet.blog_app_hd.exceptions.ResourceAlreadyExistException;
import com.ahmet.blog_app_hd.exceptions.ResourceNotFoundException;
import com.ahmet.blog_app_hd.repositories.CategoryRep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRep categoryRep;

    public Category createNew(CategoryCreateUpdateRequest request) {
        boolean isExist = categoryRep.findByTitle(request.getTitle()).isPresent();
        if (!isExist) {
            Category created = new Category();
            created.setTitle(request.getTitle());
            created.setDescription(request.getDescription());
            return categoryRep.save(created);
        } else {
            throw new ResourceAlreadyExistException("Category", "title", request.getTitle());
        }

    }

    public Category updateCat(Long id, CategoryCreateUpdateRequest request) {
        Optional<Category> target = categoryRep.findById(id);
        if (target.isPresent()) {
            Category update = target.get();
            update.setTitle(request.getTitle());
            update.setDescription(request.getDescription());
            return categoryRep.save(update);
        } else {
            throw new ResourceNotFoundException("Category", "id", id);
        }
    }

    public List<Category> getAll() {
        return categoryRep.findAll();
    }

    public Category fondOne(Long id) {
        boolean isExist = categoryRep.existsById(id);
        if (isExist) {
            return categoryRep.findById(id).get();
        } else {
            throw new ResourceNotFoundException("Category", "id", id);
        }
    }

    public void deleteCat(long id) {
        boolean isExist = categoryRep.existsById(id);
        if (isExist) {
            categoryRep.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Category", "id", id);
        }
    }
}
