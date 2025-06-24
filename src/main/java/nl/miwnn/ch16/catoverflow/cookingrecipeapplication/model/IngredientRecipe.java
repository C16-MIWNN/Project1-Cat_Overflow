package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
  * @author Robyn Blignaut, Bas Folkers
  * The concept of what you can do with an Ingredient
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
