package com.ahmet.blog_app_hd.repositories;

import com.ahmet.blog_app_hd.entities.Comment;
import com.ahmet.blog_app_hd.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRep extends JpaRepository<Comment,Long> {

    List<Comment> findByPost(Post post);
}
