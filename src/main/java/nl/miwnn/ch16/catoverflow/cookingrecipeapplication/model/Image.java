package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * @author Robyn Blignaut
 */

@Entity
public class Image {
    @Id @GeneratedValue
    private int recipeId;

    private String image;
}
