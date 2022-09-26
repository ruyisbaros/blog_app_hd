package com.ahmet.blog_app_hd.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapModel {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
