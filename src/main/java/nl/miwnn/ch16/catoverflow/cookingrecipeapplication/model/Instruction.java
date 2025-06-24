package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model;

import jakarta.persistence.*;

/**
 * @author Robyn Blignaut, Bas Folkers
 * The concept of an instruction for which the library can have a copy
 */

@Entity
public class Instruction {
    @Id @GeneratedValue
    private int instructionId;

    private String description;

    @ManyToOne
    private Recipe recipe;

    @OneToOne
    private Image image;

    public Instruction(Integer instructionId, String description) {
        this.instructionId = instructionId;
        this.description = description;
    }

    public Instruction() {

    }

    public Integer getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(Integer idInstruction) {
        this.instructionId = idInstruction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
