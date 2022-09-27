package com.ahmet.blog_app_hd.services;

import com.ahmet.blog_app_hd.entities.Image;
import com.ahmet.blog_app_hd.repositories.ImageRep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageService {

    private ImageRep imageRep;

    public void saveImage(Image image) {
        imageRep.save(image);
    }

    public boolean isImageExist(String id) {
        return imageRep.findByImageId(id).isPresent();
    }

    public Image getImage(String id) {
        Optional<Image> image = imageRep.findByImageId(id);
        return image.get();
    }

    public void deleteImage(String id) {
        imageRep.deleteByImageId(id);
    }
}
