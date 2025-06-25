package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.controller;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories.ImageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Bas Folkers
 * Schrijf hier wat je programma doet
 */

@Controller
@RequestMapping("/images")
public class ImageController {
    private final ImageRepository imageRepository;


    public ImageController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

}
