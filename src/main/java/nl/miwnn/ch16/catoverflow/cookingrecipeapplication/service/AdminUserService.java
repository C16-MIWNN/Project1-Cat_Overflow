package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.service;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.dto.NewCookingRecipeUserDTO;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.AdminUser;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories.AdminUserRepository;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.service.mappers.AdminUserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Robyn Blignaut & Bas Folkers
 */

@Service
public class AdminUserService implements UserDetailsService {
    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserService(AdminUserRepository adminUserRepository,
                            PasswordEncoder passwordEncoder) {
        this.adminUserRepository = adminUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s was not found.", username)));
    }

    public void saveUser(AdminUser adminUser) {
        adminUser.setPassword(passwordEncoder.encode(adminUser.getPassword()));
        adminUserRepository.save(adminUser);
    }

    public List<AdminUser> getAllUsers() {
        return adminUserRepository.findAll();
    }

    public boolean usernameInUse(String username) {
        return adminUserRepository.existsByUsername(username);
    }

    public void saveEmail(AdminUser adminUser, String email) {
        adminUser.setEmail(email);
        adminUserRepository.save(adminUser);
    }

    public void saveStatus(AdminUser adminUser, String status) {
        adminUser.setStatus(status);
        adminUserRepository.save(adminUser);
    }

    public void save(NewCookingRecipeUserDTO userDtoToBeSaved) {
        saveUser(AdminUserMapper.fromDTO(userDtoToBeSaved));
    }
}
