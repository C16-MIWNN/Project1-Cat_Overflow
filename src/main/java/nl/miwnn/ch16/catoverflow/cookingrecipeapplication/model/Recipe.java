package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Robyn Blignaut, Bas Folkers
 */
@Entity
public class Recipe {
    @Id @GeneratedValue
    private Long recipeId;

    private String name;
    private String description;
    private String ingredients;
    private String preparationMethod;
    private int preparationTime;
    private int portions;
    private String cookingMethod;
    private String meal;
    private String dish;
    private String kitchen;
}
