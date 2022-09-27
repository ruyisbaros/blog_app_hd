package com.ahmet.blog_app_hd.repositories;

import com.ahmet.blog_app_hd.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRep extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.title = ?1")
    Optional<Post> findByTitle(String title);
}
