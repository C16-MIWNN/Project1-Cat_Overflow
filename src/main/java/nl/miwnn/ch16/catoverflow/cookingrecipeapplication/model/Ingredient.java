package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * @author Robyn Blignaut, Bas Folkers
 * The concept of an ingredient for which the library can have a copy
 */

@Entity
public class Ingredient {
    @Id @GeneratedValue
    public Integer ingredientId;

    public String ingredient;
    public Integer quantity;
    public String unit;
    public String notes;

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
