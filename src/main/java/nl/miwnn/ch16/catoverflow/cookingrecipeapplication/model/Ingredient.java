package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * @author Robyn Blignaut, Bas Folkers
 * The concept of an ingredient for which a recipe can have one or multiple copies
 */

@Entity
public class Ingredient {

    @Id @GeneratedValue
    private Long ingredientId;

    @Column(unique=true)
    @NotBlank(message = "Ingredient name may not be empty")
    private String ingredientName;

    public Ingredient(Long ingredientId, String ingredientName) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
    }

    public Ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Ingredient() {
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredient) {
        this.ingredientName = ingredient;
    }
}
