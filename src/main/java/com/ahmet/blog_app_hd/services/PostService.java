package com.ahmet.blog_app_hd.services;

import com.ahmet.blog_app_hd.DTO.PostDto;
import com.ahmet.blog_app_hd.entities.Category;
import com.ahmet.blog_app_hd.entities.Image;
import com.ahmet.blog_app_hd.entities.Post;
import com.ahmet.blog_app_hd.entities.User;
import com.ahmet.blog_app_hd.exceptions.ResourceAlreadyExistException;
import com.ahmet.blog_app_hd.exceptions.ResourceNotFoundExceptionLongValue;
import com.ahmet.blog_app_hd.exceptions.ResourceNotFoundExceptionStringValue;
import com.ahmet.blog_app_hd.repositories.CategoryRep;
import com.ahmet.blog_app_hd.repositories.ImageRep;
import com.ahmet.blog_app_hd.repositories.PostRep;
import com.ahmet.blog_app_hd.repositories.UserRep;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {

    private PostRep postRep;
    private CloudinaryService cloudinaryService;
    private ImageRep imageRep;
    private UserRep userRep;
    private CategoryRep categoryRep;

    public Post create(PostDto request) {
        boolean isExist = postRep.findByTitle(request.getTitle()).isPresent();

        if (!isExist) {
            Post created = new Post();
            if (request.getUserId() != null) {
                User user = userRep.findById(request.getUserId())
                        .orElseThrow(() -> new ResourceNotFoundExceptionLongValue("User", "id", request.getUserId()));
                created.setUser(user);
            }
            if (request.getCategoryTitle() != null) {
                Category category = categoryRep.findByTitle(request.getCategoryTitle())
                        .orElseThrow(() -> new ResourceNotFoundExceptionStringValue("Category", "id", request.getCategoryTitle()));
                created.setCategory(category);
            }
            if (request.getImageId() != null) {
                Image image = imageRep.findByImageId(request.getImageId())
                        .orElseThrow(() -> new ResourceNotFoundExceptionStringValue("Image", "Id", request.getImageId()));
                created.setPostImage(image);
            }
            created.setTitle(request.getTitle());
            created.setContent(request.getContent());
            created.setCreatedDate(new Date());
            return postRep.save(created);
        } else {
            throw new ResourceAlreadyExistException("Post", "title", request.getTitle());
        }
    }

    public Post updatePost(Long id, PostDto request) {
        Optional<Post> target = postRep.findById(id);
        if (target.isPresent()) {
            Post update = target.get();
            if (request.getUserId() != null) {
                User user = userRep.findById(request.getUserId())
                        .orElseThrow(() -> new ResourceNotFoundExceptionLongValue("User", "id", request.getUserId()));
                update.setUser(user);
            }
            if (request.getCategoryTitle() != null) {
                Category category = categoryRep.findByTitle(request.getCategoryTitle())
                        .orElseThrow(() -> new ResourceNotFoundExceptionStringValue("Category", "id", request.getCategoryTitle()));
                update.setCategory(category);
            }
            if (request.getImageId() != null) {
                Image image = imageRep.findByImageId(request.getImageId())
                        .orElseThrow(() -> new ResourceNotFoundExceptionStringValue("Image", "Id", request.getImageId()));
                update.setPostImage(image);
            }

            update.setTitle(request.getTitle());
            update.setContent(request.getContent());
            update.setCreatedDate(new Date());
            return postRep.save(update);
        } else {
            throw new ResourceNotFoundExceptionLongValue("Post", "id", id);
        }
    }

    public void delete(Long id) throws IOException {

        boolean isExist = postRep.existsById(id);
        if (isExist) {
            Image image = postRep.findById(id).get().getPostImage();
            cloudinaryService.deleteImage(image.getImageId());
            imageRep.deleteByImageId(image.getImageId());
            postRep.deleteById(id);
        } else {
            throw new ResourceNotFoundExceptionLongValue("Post", "id", id);
        }
    }

    public List<Post> getAll() {
        return postRep.findAll();
    }

    public Post getOne(Long id) {
        return postRep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionLongValue("Post", "id", id));
    }

    public List<Post> getPostsByCategory(String categoryTitle) {
        Category category = categoryRep.findByTitle(categoryTitle)
                .orElseThrow(() -> new ResourceNotFoundExceptionStringValue("Category", "categoryTitle", categoryTitle));
        return postRep.findByCategory(category);
    }

    public List<Post> getPostsByUser(Long id) {
        User user = userRep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionLongValue("User", "UserId", id));
        ;
        return postRep.findByUser(user);
    }

    //By Keyword
//    public List<Post> getByKeyWord(String keyword){
//        return postRep.findByKeyWord(keyword);
//    }


    public Page<Post> postsPaginatedSorted(int pageSize, int pageNo, String sortField,
                                           String sortDir, String keyword) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
        return postRep.findByKeyWord(keyword, pageable);
    }
}
