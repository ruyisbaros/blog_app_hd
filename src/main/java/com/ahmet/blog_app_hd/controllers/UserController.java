package com.ahmet.blog_app_hd.controllers;

import com.ahmet.blog_app_hd.DTO.ApiResponse;
import com.ahmet.blog_app_hd.DTO.LoginDto;
import com.ahmet.blog_app_hd.DTO.UserDto;
import com.ahmet.blog_app_hd.entities.User;
import com.ahmet.blog_app_hd.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public User getOne(@PathVariable Long id) {
//        User finded = userService.getOne(id);
//        return new ResponseEntity<>(finded, HttpStatus.OK);
        return userService.getOne(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@Valid @RequestBody UserDto request) {
        User updatedUser = userService.update(id, request);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    //@PreAuthorize("#n == authentication.name")
    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(new ApiResponse(String.format("User with %s ID has been deleted", id), true), HttpStatus.OK);
    }
}
