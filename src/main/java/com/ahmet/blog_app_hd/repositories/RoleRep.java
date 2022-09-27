package com.ahmet.blog_app_hd.repositories;

import com.ahmet.blog_app_hd.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRep extends JpaRepository<Role,String> {

    @Query("select r from Role r where r.roleName = ?1")
    Role findByRoleName(String roleName);
}
