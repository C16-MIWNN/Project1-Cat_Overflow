package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.controller;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.dto.NewCookingRecipeUserDTO;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.service.AdminUserService;
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
public class AdminUserController {
    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping("/overview")
    private String showUserOverview(Model datamodel) {
        datamodel.addAttribute("allUsers", adminUserService.getAllUsers());
        datamodel.addAttribute("formUser", new NewCookingRecipeUserDTO());
        datamodel.addAttribute("formModalHidden", true);

        return "userOverview";
    }

    @PostMapping("/save")
    private String saveOrUpdateUser(@ModelAttribute("formUser")
                                        NewCookingRecipeUserDTO userDtoToBeSaved,
                                        BindingResult result,
                                        Model datamodel) {
        if (adminUserService.usernameInUse(userDtoToBeSaved.getUsername())) {
            result.rejectValue("username", "duplicate", "This username is not available");
        }

        if (!userDtoToBeSaved.getPassword().equals(userDtoToBeSaved.getConfirmPassword())) {
            result.rejectValue("password", "no.match", "The passwords do not match");
        }

        if (result.hasErrors()) {
            datamodel.addAttribute("allUsers", adminUserService.getAllUsers());
            datamodel.addAttribute("formModalHidden", false);
            return "userOverview";
        }

        adminUserService.save(userDtoToBeSaved);
        return "redirect:/user/overview";
    }
}
