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

    private String ingredient;
    private int quantity;
    private String unit;
    private String notes;

    @ManyToOne
    private IngredientRecipe ingredientRecipe;

    public Ingredient(Integer ingredientId, String ingredient, Integer quantity, String unit, String notes) {
        this.ingredientId = ingredientId;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.unit = unit;
        this.notes = notes;
    }

    public Ingredient() {

    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer idIngredient) {
        this.ingredientId = idIngredient;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
