package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model;

import jakarta.persistence.*;

/**
 * @author Robyn Blignaut, Bas Folkers
 * The concept of an instruction for which a recipe can have one or multiple copies
 */

@Entity
public class Instruction {
    @Id @GeneratedValue
    private int instructionId;

    private String description;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public Instruction(int instructionId, String description) {
        this.instructionId = instructionId;
        this.description = description;
    }

    public Instruction() {
    }

    public int getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(int instructionId) {
        this.instructionId = instructionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
