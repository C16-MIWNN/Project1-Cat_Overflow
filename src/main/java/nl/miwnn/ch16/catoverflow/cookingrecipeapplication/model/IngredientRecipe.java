package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

/**
  * @author Robyn Blignaut, Bas Folkers
  * The concept of an ingredient that belongs to a recipe and how many/much of it you need for that recipe.
 */

@Entity
public class IngredientRecipe {

    @Id @GeneratedValue
    private Long ingredientRecipeId;

    @ManyToOne
    @NotNull(message = "Ingredient may not be null")
    private Ingredient ingredient;

    @ManyToOne
    private Recipe recipe;

    private int quantity;
    private String ingredientUnit;
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

    public String getIngredientUnit() {
        return ingredientUnit;
    }

    public void setIngredientUnit(String ingredientUnit) {
        this.ingredientUnit = ingredientUnit;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (quantity != 0) {
            stringBuilder.append(quantity).append(" ");
        }

        if (ingredientUnit != null && !ingredientUnit.isBlank()) {
            stringBuilder.append(ingredientUnit).append(" ");
        }

        stringBuilder.append(ingredient.getIngredientName());

        if (notes != null && !notes.isBlank()) {
            stringBuilder.append(" (").append(notes).append(")");
        }

        return stringBuilder.toString().trim();
    }
}
