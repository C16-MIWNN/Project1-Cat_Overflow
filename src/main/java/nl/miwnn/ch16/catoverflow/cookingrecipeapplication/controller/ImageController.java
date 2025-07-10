package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.controller;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories.ImageRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Bas Folkers
 * Handle all requests related to images for recipes
 */

@Controller
@RequestMapping("/images")
public class ImageController {
    private final ImageRepository imageRepository;

    public ImageController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @GetMapping("/{imageId}")
    @ResponseBody
    public ResponseEntity<Resource> serveImage(@PathVariable int imageId) {
        Path imagePath = Path.of("src/main/resources/static/uploads", imageId + ".png");

        try {
            Resource resource = new UrlResource(imagePath.toUri());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(imagePath))
                    .body(resource);
        } catch (IOException ioException) {
            return ResponseEntity.notFound().build();
        }
    }
}
