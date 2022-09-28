package com.ahmet.blog_app_hd.services;

import com.ahmet.blog_app_hd.DTO.UserDto;
import com.ahmet.blog_app_hd.entities.Image;
import com.ahmet.blog_app_hd.entities.Role;
import com.ahmet.blog_app_hd.entities.User;
import com.ahmet.blog_app_hd.exceptions.ResourceAlreadyExistException;
import com.ahmet.blog_app_hd.exceptions.ResourceNotFoundExceptionLongValue;
import com.ahmet.blog_app_hd.repositories.ImageRep;
import com.ahmet.blog_app_hd.repositories.RoleRep;
import com.ahmet.blog_app_hd.repositories.UserRep;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRep userRep;
    private RoleRep roleRep;
    private ImageRep imageRep;

    private PasswordEncoder passwordEncoder;

    public User create(UserDto request) {
        boolean isExist = userRep.findByEmail(request.getEmail()).isPresent();
        if (!isExist) {
            User newUser = new User();
            if (request.getRoles() != null) {
                List<Role> roles = new ArrayList<>();
                for (Role role : request.getRoles()) {
                    roles.add(new Role(role.getRoleName()));
                }
                newUser.setRoles(roles);
            }

            if (request.getImageId() != null) {
                Optional<Image> image = imageRep.findByImageId(request.getImageId());
                newUser.setImage(image.get());
            }
            newUser.setName(request.getName());
            newUser.setEmail(request.getEmail());
            newUser.setAbout(request.getAbout());
            newUser.setPassword(passwordEncoder.encode(request.getPassword()));
            return userRep.save(newUser);
        } else {
            throw new ResourceAlreadyExistException("User", "email", request.getEmail());
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
            throw new ResourceNotFoundExceptionLongValue("User", "id", id);
        }
    }

    public User update(Long id, UserDto request) {
        Optional<User> user = userRep.findById(id);
        if (user.isPresent()) {
            User updatedUser = user.get();

            List<Role> roles = new ArrayList<>();
            for (Role role : request.getRoles()) {
                roles.add(new Role(role.getRoleName()));
            }
            updatedUser.setRoles(roles);

            if (request.getImageId() != null) {
                Optional<Image> image = imageRep.findByImageId(request.getImageId());
                updatedUser.setImage(image.get());
            }

            updatedUser.setName(request.getName());
            updatedUser.setEmail(request.getEmail());
            updatedUser.setAbout(request.getAbout());
            updatedUser.setPassword(passwordEncoder.encode(request.getPassword()));
            return userRep.save(updatedUser);
        } else {
            throw new ResourceNotFoundExceptionLongValue("User", "id", id);
        }
    }

    public void deleteUser(Long id) {
        Optional<User> deleteUser = userRep.findById(id);
        if (deleteUser.isPresent()) {
            userRep.delete(deleteUser.get());
        } else {
            throw new ResourceNotFoundExceptionLongValue("User", "id", id);
        }
    }
}
