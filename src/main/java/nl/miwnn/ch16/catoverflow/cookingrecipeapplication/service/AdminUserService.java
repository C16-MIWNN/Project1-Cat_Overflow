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

    public void save(NewCookingRecipeUserDTO dto) {
        AdminUser user;

        if (dto.getOriginalUsername() != null && adminUserRepository.findByUsername(dto.getOriginalUsername()).isPresent()) {
            user = adminUserRepository.findByUsername(dto.getOriginalUsername()).get();
        } else {
            user = new AdminUser();
        }

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setStatus(dto.getStatus());

        adminUserRepository.save(user);
    }

    public NewCookingRecipeUserDTO getUserDTOByUsername(String username) {
        AdminUser user = adminUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        NewCookingRecipeUserDTO dto = new NewCookingRecipeUserDTO();
        dto.setUsername(user.getUsername());
        dto.setOriginalUsername(dto.getOriginalUsername());
        dto.setEmail(user.getEmail());
        dto.setStatus(user.getStatus());
        dto.setPassword("");
        dto.setConfirmPassword("");
        return dto;
    }
}
