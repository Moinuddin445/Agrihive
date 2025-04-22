package com.DigiMarket.AgriHive.controller;

import org.springframework.beans.factory.annotation.Value; // Import Value for injecting properties
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/Pimages")
public class ImageController {

    @Value("${static.Pimages}") // Injecting the file path from application.properties
    private String uploadDir;

    @GetMapping("/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) throws MalformedURLException {
        // Construct the full image path
        Path imagePath = Paths.get(uploadDir).resolve(imageName);
        Resource resource = new UrlResource(imagePath.toUri());

        // Check if the resource exists and is readable
        if (resource.exists() && resource.isReadable()) {
            // Dynamically determine the content type based on the file extension
            String mimeType = MimeTypeUtils.IMAGE_JPEG_VALUE;
            if (imageName.toLowerCase().endsWith(".png")) {
                mimeType = MimeTypeUtils.IMAGE_PNG_VALUE;
            } else if (imageName.toLowerCase().endsWith(".gif")) {
                mimeType = MimeTypeUtils.IMAGE_GIF_VALUE;
            }

            // Return the image with the correct content type
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(mimeType))
                    .body(resource);
        } else {
            // Return 404 if image doesn't exist or is unreadable
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
