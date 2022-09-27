package com.ahmet.blog_app_hd.controllers;

import com.ahmet.blog_app_hd.DTO.ApiResponse;
import com.ahmet.blog_app_hd.entities.Image;
import com.ahmet.blog_app_hd.exceptions.ResourceAlreadyExistException;
import com.ahmet.blog_app_hd.services.CloudinaryService;
import com.ahmet.blog_app_hd.services.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/images")
@AllArgsConstructor
public class ImageController {

    private ImageService imageService;
    private CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile multipartFile) throws IOException {
        Map result = cloudinaryService.uploadImage(multipartFile);

        Image image = new Image((String) result.get("public_id"), (String) result.get("url"));
        imageService.saveImage(image);
        return ResponseEntity.ok(image);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable String id) throws IOException {
        if (imageService.isImageExist(id)) {
            Image found = imageService.getImage(id);
            cloudinaryService.deleteImage(found.getImageId());
            imageService.deleteImage(id);
            return new ResponseEntity<>(new ApiResponse("Image has been deleted", true),
                    HttpStatus.OK);
        } else {
            //New exception class i must create (later)
            throw new ResourceAlreadyExistException("Image", "ID", id);
        }
    }
}
