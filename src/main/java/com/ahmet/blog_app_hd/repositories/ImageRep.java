package com.ahmet.blog_app_hd.repositories;

import com.ahmet.blog_app_hd.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ImageRep extends JpaRepository<Image,Long> {

   @Query("select i from Image i where i.imageId = ?1")
    Optional<Image> findByImageId(String imageId);

   void deleteByImageId(String imageId);
}
