package com.ahmet.blog_app_hd.repositories;

import com.ahmet.blog_app_hd.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRep extends JpaRepository<User,Long> {

    @Query("select u from User u where u.email = ?1")
    Optional<User> findByEmail(String email);
}
