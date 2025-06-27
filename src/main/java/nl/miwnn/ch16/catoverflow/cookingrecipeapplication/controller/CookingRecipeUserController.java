package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.controller;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.dto.NewCookingRecipeUserDTO;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.service.CookingRecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Robyn Blignaut & Bas Folkers
 * Handles all requests related to Cooking Recipe users
 */

@Controller
@RequestMapping("/user")
public class CookingRecipeUserController {
    private final CookingRecipeService cookingRecipeService;

    public CookingRecipeUserController(CookingRecipeService cookingRecipeService) {
        this.cookingRecipeService = cookingRecipeService;
    }

    @GetMapping("/overview")
    private String showUserOverview(Model datamodel) {
        datamodel.addAttribute("allUsers", cookingRecipeService.getAllUsers());
        datamodel.addAttribute("formUser", new NewCookingRecipeUserDTO());
        datamodel.addAttribute("formModalHidden", true);

        return "userOverview";
    }

    @PostMapping("/save")
    private String saveOrUpdateUser(@ModelAttribute("formUser")
                                        NewCookingRecipeUserDTO userDtoToBeSaved,
                                        BindingResult result,
                                        Model datamodel) {
        if (cookingRecipeService.usernameInUse(userDtoToBeSaved.getUsername())) {
            result.rejectValue("username", "duplicate", "This username is not available");
        }

        if (!userDtoToBeSaved.getPassword().equals(userDtoToBeSaved.getConfirmPassword())) {
            result.rejectValue("password", "no.match", "The passwords do not match");
        }

        if (result.hasErrors()) {
            datamodel.addAttribute("allUsers", cookingRecipeService.getAllUsers());
            datamodel.addAttribute("formModalHidden", false);
            return "userOverview";
        }

        cookingRecipeService.save(userDtoToBeSaved);
        return "redirect:/user/overview";
    }
}
