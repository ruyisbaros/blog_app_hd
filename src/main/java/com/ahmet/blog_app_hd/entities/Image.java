package com.ahmet.blog_app_hd.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageId;
    private String imageUrl;

    public Image(String imageId, String imageUrl) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
    }

    public Image(String imageId) {
        this.imageId = imageId;
    }
}

