package com.ahmet.blog_app_hd.repositories;

import com.ahmet.blog_app_hd.entities.Category;
import com.ahmet.blog_app_hd.entities.Post;
import com.ahmet.blog_app_hd.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRep extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.title = ?1")
    Optional<Post> findByTitle(String title);

    List<Post> findByUser(User user);
    Page<Post> findByCategory(Category category,Pageable pageable);

    @Query("select p from Post p where p.title like %?1% or p.content like %?1%")
    Page<Post> findByKeyWord(String keyword, Pageable pageable);
}
