package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * @author Robyn Blignaut, Bas Folkers
 * The concept of an ingredient for which a recipe can have one or multiple copies
 */

@Entity
public class Ingredient {

    @Id @GeneratedValue
    private Long ingredientId;

    @Column(unique=true)
    private String ingredientName;

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long idIngredient) {
        this.ingredientId = idIngredient;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredient) {
        this.ingredientName = ingredient;
    }
}
