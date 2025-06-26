package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.service;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.CookingRecipeUser;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories.CookingRecipeUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Robyn Blignaut & Bas Folkers
 */

@Service
public class CookingRecipeService implements UserDetailsService {
    private final CookingRecipeUserRepository cookingRecipeUserRepository;
    private final PasswordEncoder passwordEncoder;

    public CookingRecipeService(CookingRecipeUserRepository cookingRecipeUserRepository,
                                PasswordEncoder passwordEncoder) {
        this.cookingRecipeUserRepository = cookingRecipeUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return cookingRecipeUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s was not found.", username)));
    }

    public void saveUser(CookingRecipeUser cookingRecipeUser) {
        cookingRecipeUser.setPassword(passwordEncoder.encode(cookingRecipeUser.getPassword()));
        cookingRecipeUserRepository.save(cookingRecipeUser);
    }
}
