package com.ahmet.blog_app_hd.configurations;

import com.ahmet.blog_app_hd.entities.User;
import com.ahmet.blog_app_hd.exceptions.ResourceNotFoundExceptionStringValue;
import com.ahmet.blog_app_hd.repositories.UserRep;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private UserRep userRep;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRep.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundExceptionStringValue("User", "email", email));

        return user;
    }
}
