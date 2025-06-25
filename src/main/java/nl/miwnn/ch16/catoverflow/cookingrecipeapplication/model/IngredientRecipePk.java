package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

/**
 * @author Robyn Blignaut
 */

@Embeddable
public class IngredientRecipePk implements Serializable {
    private int ingredientId;
    private int recipeId;

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
}
