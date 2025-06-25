package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
  * @author Robyn Blignaut, Bas Folkers
  * The concept of an ingredient that belongs to a recipe and how many/much of it you need for that recipe.
 */

@Entity
public class IngredientRecipe {

    @Id @ManyToOne
    private Ingredient ingredient;

    @Id @ManyToOne
    private Recipe recipe;

    private int quantity;
    private String Unit;
    private String notes;
}
