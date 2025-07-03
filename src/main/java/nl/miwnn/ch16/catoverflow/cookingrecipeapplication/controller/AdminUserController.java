package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.controller;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.dto.NewCookingRecipeUserDTO;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.AdminUser;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories.AdminUserRepository;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.service.AdminUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author Robyn Blignaut & Bas Folkers
 * Handles all requests related to Cooking Recipe users
 */

@Controller
@RequestMapping("/user")
public class AdminUserController {
    private final AdminUserService adminUserService;
    private final AdminUserRepository adminUserRepository;

    public AdminUserController(AdminUserService adminUserService, AdminUserRepository adminUserRepository) {
        this.adminUserService = adminUserService;
        this.adminUserRepository = adminUserRepository;
    }

    @GetMapping("/overview")
    private String showUserOverview(Model datamodel) {
        datamodel.addAttribute("allUsers", adminUserService.getAllUsers());
        datamodel.addAttribute("formUser", new NewCookingRecipeUserDTO());
        datamodel.addAttribute("formModalHidden", true);

        return "userOverview";
    }

    @PostMapping("/save")
    private String saveNewUser(@ModelAttribute("formUser")
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

    @GetMapping("/edit")
    private String editUser(@RequestParam("username") String username, Model model) {
        AdminUser userToEdit = adminUserRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        NewCookingRecipeUserDTO dto = new NewCookingRecipeUserDTO();
        dto.setUsername(userToEdit.getUsername());
        dto.setEmail(userToEdit.getEmail());
        dto.setStatus(userToEdit.getStatus());

        model.addAttribute("formUser", dto);
        model.addAttribute("allUsers", adminUserService.getAllUsers());
        model.addAttribute("formModalHidden", false);

        return "userOverview";
    }
}