package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model;

import jakarta.persistence.*;

/**
  * @author Robyn Blignaut, Bas Folkers
  * The concept of an ingredient that belongs to a recipe and how many/much of it you need for that recipe.
 */

@Entity
public class IngredientRecipe {

    @Id @GeneratedValue
    private Long ingredientRecipeId;

    @ManyToOne
    private Ingredient ingredient;

    @ManyToOne
    private Recipe recipe;

    private int quantity;
    private String unit;
    private String notes;

    public Long getIngredientRecipeId() {
        return ingredientRecipeId;
    }

    public void setIngredientRecipeId(Long ingredientRecipeId) {
        this.ingredientRecipeId = ingredientRecipeId;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        unit = unit;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
