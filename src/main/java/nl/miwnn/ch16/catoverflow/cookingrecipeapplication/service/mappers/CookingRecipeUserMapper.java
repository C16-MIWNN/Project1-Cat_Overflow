package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.service.mappers;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.dto.NewCookingRecipeUserDTO;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.CookingRecipeUser;

/**
 * @author Robyn Blignaut & Bas Folkers
 * Convert NewCookingRecipeDTO objects into CookingRecipeUsers
 */

public class CookingRecipeUserMapper {

    public static CookingRecipeUser fromDTO(NewCookingRecipeUserDTO newCookingRecipeUserDTO) {
        CookingRecipeUser cookingRecipeUser = new CookingRecipeUser();

        cookingRecipeUser.setUsername(newCookingRecipeUserDTO.getUsername());
        cookingRecipeUser.setPassword(newCookingRecipeUserDTO.getPassword());

        return cookingRecipeUser;
    }

}
