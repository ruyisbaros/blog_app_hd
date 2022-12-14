package com.ahmet.blog_app_hd.controllers;

import com.ahmet.blog_app_hd.DTO.LoginDto;
import com.ahmet.blog_app_hd.DTO.LoginResponse;
import com.ahmet.blog_app_hd.DTO.UserDto;
import com.ahmet.blog_app_hd.JWT.JwtCreate;
import com.ahmet.blog_app_hd.entities.User;
import com.ahmet.blog_app_hd.exceptions.AccessDeniedException;
import com.ahmet.blog_app_hd.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtCreate jwtCreate;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto request) {
        User createdUser = userService.create(request);
        String jwtToken = jwtCreate.createToken(createdUser);
        Object[] regResponse = {jwtToken, createdUser};
        return new ResponseEntity<>(regResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            String jwtToken = jwtCreate.createToken(user);
            Object[] regResponse = {jwtToken, user};
            return new ResponseEntity<>(regResponse, HttpStatus.CREATED);

        } catch (BadCredentialsException e) {
            throw new AccessDeniedException("Invalid username or password");
        }
    }

}
