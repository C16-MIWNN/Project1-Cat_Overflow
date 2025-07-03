package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.service.mappers;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.dto.NewCookingRecipeUserDTO;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.AdminUser;

/**
 * @author Robyn Blignaut & Bas Folkers
 * Convert NewCookingRecipeDTO objects into CookingRecipeUsers
 */

public class AdminUserMapper {

    public static AdminUser fromDTO(NewCookingRecipeUserDTO newCookingRecipeUserDTO) {
        AdminUser adminUser = new AdminUser();

        adminUser.setUsername(newCookingRecipeUserDTO.getUsername());
        adminUser.setPassword(newCookingRecipeUserDTO.getPassword());
        adminUser.setEmail(newCookingRecipeUserDTO.getEmail());
        adminUser.setStatus(newCookingRecipeUserDTO.getStatus());

        return adminUser;
    }

}
