package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.Instruction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstructionRepository extends JpaRepository<Instruction, Long> {
    Optional<Instruction> findByInstructionId(int instructionId);
}
