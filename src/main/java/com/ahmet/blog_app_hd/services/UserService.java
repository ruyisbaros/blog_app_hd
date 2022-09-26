package com.ahmet.blog_app_hd.services;

import com.ahmet.blog_app_hd.DTO.UserCreateUpdateRequest;
import com.ahmet.blog_app_hd.entities.User;
import com.ahmet.blog_app_hd.exceptions.ResourceNotFoundException;
import com.ahmet.blog_app_hd.repositories.UserRep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRep userRep;

    public User create(UserCreateUpdateRequest request) {
        boolean isExist = userRep.findByEmail(request.getEmail()).isPresent();
        if (!isExist) {
            User newUser = new User();
            newUser.setName(request.getName());
            newUser.setEmail(request.getEmail());
            newUser.setAbout(request.getAbout());
            newUser.setPassword(request.getPassword());
            return userRep.save(newUser);
        } else {
            throw new RuntimeException(String.format("User with %s email id is already exist", request.getEmail()));
        }
    }

    public List<User> getAll() {
        return userRep.findAll();
    }

    public User getOne(Long id) {
        Optional<User> searchedUser = userRep.findById(id);
        if (searchedUser.isPresent()) {
            return searchedUser.get();
        } else {
            throw new ResourceNotFoundException("User", "id", id);
        }
    }

    public User update(Long id, UserCreateUpdateRequest request) {
        Optional<User> user = userRep.findById(id);
        if (user.isPresent()) {
            User updatedUser = user.get();
            if (request.getName() != null) updatedUser.setName(request.getName());
            if (request.getEmail() != null) updatedUser.setEmail(request.getEmail());
            if (request.getAbout() != null) updatedUser.setAbout(request.getAbout());
            if (request.getPassword() != null) updatedUser.setPassword(request.getPassword());
            return userRep.save(updatedUser);
        } else {
            throw new ResourceNotFoundException("User", "id", id);
        }
    }

    public void deleteUser(Long id) {
        Optional<User> deleteUser = userRep.findById(id);
        if (deleteUser.isPresent()) {
            userRep.delete(deleteUser.get());
        } else {
            throw new ResourceNotFoundException("User", "id", id);
        }
    }
}
