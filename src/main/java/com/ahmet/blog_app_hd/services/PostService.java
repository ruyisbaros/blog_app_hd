package com.ahmet.blog_app_hd.services;

import com.ahmet.blog_app_hd.DTO.PostCreateUpdateRequest;
import com.ahmet.blog_app_hd.entities.Category;
import com.ahmet.blog_app_hd.entities.Post;
import com.ahmet.blog_app_hd.exceptions.ResourceAlreadyExistException;
import com.ahmet.blog_app_hd.exceptions.ResourceNotFoundException;
import com.ahmet.blog_app_hd.repositories.PostRep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {

    private PostRep postRep;

    public Post create(PostCreateUpdateRequest request) {
        boolean isExist = postRep.findByTitle(request.getTitle()).isPresent();

        if (!isExist) {
            Post created = new Post();
            created.setTitle(request.getTitle());
            created.setContent(request.getContent());
            return postRep.save(created);
        } else {
            throw new ResourceAlreadyExistException("Category", "title", request.getTitle());
        }
    }

    public Post updateCat(Long id, PostCreateUpdateRequest request) {
        Optional<Post> target = postRep.findById(id);
        if (target.isPresent()) {
            Post update = target.get();
            update.setTitle(request.getTitle());
            update.setContent(request.getContent());
            return postRep.save(update);
        } else {
            throw new ResourceNotFoundException("Post", "id", id);
        }
    }

    public void delete(Long id) {

        boolean isExist = postRep.existsById(id);
        if (isExist) {
            postRep.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Post", "id", id);
        }
    }

    public List<Post> getAll() {
        return postRep.findAll();
    }

    public Post getOne(Long id) {
        boolean isExist = postRep.existsById(id);
        if (isExist) {
            return postRep.findById(id).get();
        } else {
            throw new ResourceNotFoundException("Post", "id", id);
        }
    }
}
