package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * @author Robyn Blignaut, Bas Folkers
 * A concept of an image which belongs to a recipe and can add multiple or a single image.
 */

@Entity
public class Image {
    @Id @GeneratedValue
    private int recipeId;

    private String image;
}
