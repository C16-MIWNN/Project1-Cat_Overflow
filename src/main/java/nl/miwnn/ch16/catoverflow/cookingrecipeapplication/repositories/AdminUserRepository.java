package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {
    Optional<AdminUser> findByUsername(String username);

    boolean existsByUsername(String username);
}
