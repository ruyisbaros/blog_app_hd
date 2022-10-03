package com.ahmet.blog_app_hd.repositories;

import com.ahmet.blog_app_hd.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRep extends JpaRepository<Category, Long> {

    @Query("select c from Category c where c.title = ?1")
    Optional<Category> findByTitle(String title);


}
