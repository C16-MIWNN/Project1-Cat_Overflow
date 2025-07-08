package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model;

/**
 * Enum representing difficulty levels for a recipe.
 * @author Robyn Blignaut
 */

public enum DifficultyLevel {
    VERY_EASY("Very Easy"),
    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard"),
    VERY_HARD("Very Hard");

    private final String label;

    DifficultyLevel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
