package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Robyn Blignaut, Bas Folkers
 * The concept of a recipe for which the library can have a copy
 */

@Entity
public class Recipe {
    @Id @GeneratedValue
    private int recipeId;

    private String title;
    private String summary;
    private String description;
    private int portionQuantity;
    private String portionUnit;
    private int totalCookingTime;

    @OneToOne
    private Image image;

    @OneToMany
    private List<Instruction> instructions = new ArrayList<>();

    @OneToMany
    private List<IngredientRecipe> ingredients = new ArrayList<>();

    public Recipe(Integer recipeId, String title, String summary, String description,
                  Integer portionQuantity, String portionUnit, Integer totalCookingTime) {
        this.recipeId = recipeId;
        this.title = title;
        this.summary = summary;
        this.description = description;
        this.portionQuantity = portionQuantity;
        this.portionUnit = portionUnit;
        this.totalCookingTime = totalCookingTime;
    }

    public Recipe() {

    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer idRecipe) {
        this.recipeId = idRecipe;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPortionQuantity() {
        return portionQuantity;
    }

    public void setPortionQuantity(Integer portionQuantity) {
        this.portionQuantity = portionQuantity;
    }

    public String getPortionUnit() {
        return portionUnit;
    }

    public void setPortionUnit(String portionUnit) {
        this.portionUnit = portionUnit;
    }

    public Integer getTotalCookingTime() {
        return totalCookingTime;
    }

    public void setTotalCookingTime(Integer totalCookingTime) {
        this.totalCookingTime = totalCookingTime;
    }
}

