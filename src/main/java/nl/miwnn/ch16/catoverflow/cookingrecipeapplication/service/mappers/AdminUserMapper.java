package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.service.mappers;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.dto.NewUserDTO;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.AdminUser;

/**
 * @author Robyn Blignaut & Bas Folkers
 * Convert NewCookingRecipeDTO objects into CookingRecipeUsers
 */

public class AdminUserMapper {

    public static AdminUser fromDTO(NewUserDTO newUserDTO) {
        AdminUser adminUser = new AdminUser();

        adminUser.setUsername(newUserDTO.getUsername());
        adminUser.setPassword(newUserDTO.getPassword());
        adminUser.setEmail(newUserDTO.getEmail());
        adminUser.setStatus(newUserDTO.getStatus());

        return adminUser;
    }

}
