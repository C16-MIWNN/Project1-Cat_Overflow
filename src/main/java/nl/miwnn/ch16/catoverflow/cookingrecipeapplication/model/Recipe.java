package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Robyn Blignaut, Bas Folkers
 * The concept of a recipe for which consists of multiple entities.
 */

@Entity
public class Recipe {

    public static final double MIN_ING_DIFF = 3.0;
    public static final double MAX_ING_DIFF = 15.0;
    public static final double MIN_STEP_DIFF = 1.0;
    public static final double MAX_STEP_DIFF = 20.0;
    public static final double WEIGHT_ING_DIFF = 0.5;
    public static final double WEIGHT_STEP_DIFF = 0.5;
    @Id @GeneratedValue
    private Long recipeId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @Column(columnDefinition = "TEXT")
    private String description;

    private int portionQuantity;
    private String portionUnit;
    private int totalCookingTime;

    @OneToOne
    private Image image;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IngredientRecipe> ingredients = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Instruction> instructions = new ArrayList<>();

    public Recipe(Long recipeId,
                  String title,
                  String summary,
                  String description,
                  int portionQuantity,
                  String portionUnit,
                  int totalCookingTime,
                  Image image,
                  List<IngredientRecipe> ingredients,
                  List<Instruction> instructions) {
        this.recipeId = recipeId;
        this.title = title;
        this.summary = summary;
        this.description = description;
        this.portionQuantity = portionQuantity;
        this.portionUnit = portionUnit;
        this.totalCookingTime = totalCookingTime;
        this.image = image;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public Recipe() {
    }

    public int getIngredientCount() {
        return ingredients.size();
    }

    public int getStepCount() {
        return instructions.size();
    }

    public int getDifficulty() {
        double normIngredient = normalize(getIngredientCount(), MIN_ING_DIFF, MAX_ING_DIFF);
        double normStep = normalize(getStepCount(), MIN_STEP_DIFF, MAX_STEP_DIFF);
        double score = WEIGHT_ING_DIFF * normIngredient + WEIGHT_STEP_DIFF * normStep;

        if (score <= 0.2) {
            return 1;
        } else if (score <= 0.4) {
            return 2;
        } else if (score <= 0.6) {
            return 3;
        } else if (score <= 0.8) {
            return 4;
        }
        return 5;
    }

    private double normalize(int count, double min, double max) {
        if (count <= min) return 0.0;
        if (count >= max) return 1.0;
        return (count - min) / (max - min);
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
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

    public int getPortionQuantity() {
        return portionQuantity;
    }

    public void setPortionQuantity(int portionQuantity) {
        this.portionQuantity = portionQuantity;
    }

    public String getPortionUnit() {
        return portionUnit;
    }

    public void setPortionUnit(String portionUnit) {
        this.portionUnit = portionUnit;
    }

    public int getTotalCookingTime() {
        return totalCookingTime;
    }

    public void setTotalCookingTime(int totalCookingTime) {
        this.totalCookingTime = totalCookingTime;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<IngredientRecipe> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientRecipe> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }
}

