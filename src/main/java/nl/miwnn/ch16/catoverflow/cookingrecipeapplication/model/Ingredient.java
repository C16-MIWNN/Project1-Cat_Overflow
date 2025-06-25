package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * @author Robyn Blignaut, Bas Folkers
 * The concept of an ingredient for which a recipe can have one or multiple copies
 */

@Entity
public class Ingredient {
    @Id @GeneratedValue
    private int ingredientId;

    private String ingredientName;

    public Ingredient(int ingredientId, String ingredientName, IngredientRecipe ingredientRecipe) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.ingredientRecipe = ingredientRecipe;
    }

    @ManyToOne
    private IngredientRecipe ingredientRecipe;

    public Ingredient() {

    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer idIngredient) {
        this.ingredientId = idIngredient;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredient) {
        this.ingredientName = ingredient;
    }

}
