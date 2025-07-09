package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model;

import jakarta.annotation.Nullable;
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
    public static final double MIN_SCORE_VERY_EASY = 0.2;
    public static final double MIN_SCORE_EASY = 0.4;
    public static final double MIN_SCORE_MEDIUM = 0.6;
    public static final double MIN_SCORE_HARD = 0.8;
    public static final double MIN_NORMALIZE = 0.0;
    public static final double MAX_NORMALIZE = 1.0;
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
    @Nullable
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
                  @Nullable Image image,
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
        if (ingredients == null) {
            return 0;
        }
        return ingredients.size();
    }

    public int getStepCount() {
        if (ingredients == null) {
            return 0;
        }
        return instructions.size();
    }

    public DifficultyLevel getDifficulty() {
        double normIngredient = normalize(getIngredientCount(), MIN_ING_DIFF, MAX_ING_DIFF);
        double normStep = normalize(getStepCount(), MIN_STEP_DIFF, MAX_STEP_DIFF);
        double score = WEIGHT_ING_DIFF * normIngredient + WEIGHT_STEP_DIFF * normStep;

        if (score <= MIN_SCORE_VERY_EASY) return DifficultyLevel.VERY_EASY;
        if (score <= MIN_SCORE_EASY) return DifficultyLevel.EASY;
        if (score <= MIN_SCORE_MEDIUM) return DifficultyLevel.MEDIUM;
        if (score <= MIN_SCORE_HARD) return DifficultyLevel.HARD;
        return DifficultyLevel.VERY_HARD;
    }

    private double normalize(int count, double min, double max) {
        if (count <= min) return MIN_NORMALIZE;
        if (count >= max) return MAX_NORMALIZE;
        return (count - min) / (max - min);
    }

    public int getDifficultyNumber() {
        switch (getDifficulty()) {
            case VERY_EASY:
                return 1;
            case EASY:
                return 2;
            case MEDIUM:
                return 3;
            case HARD:
                return 4;
            case VERY_HARD:
                return 5;
            default:
                return 0;
        }
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

