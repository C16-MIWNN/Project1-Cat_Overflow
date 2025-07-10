package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.controller;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.dto.NewUserDTO;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories.AdminUserRepository;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.service.AdminUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String showUserOverview(Model model) {
        if (!model.containsAttribute("formUser")) {
            model.addAttribute("formUser", new NewUserDTO());
            model.addAttribute("formModalHidden", true);
        }
        model.addAttribute("allUsers", adminUserService.getAllUsers());
        return "userOverview";
    }

    @PostMapping("/save")
    public String saveNewUser(@ModelAttribute("formUser") NewUserDTO userDtoToBeSaved,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {

        if (userDtoToBeSaved.getUsername() == null || userDtoToBeSaved.getUsername().isBlank()) {
            result.rejectValue("username", "empty", "Username mag niet leeg zijn");
        }

        if (!userDtoToBeSaved.getUsername().equals(userDtoToBeSaved.getOriginalUsername()) &&
                adminUserService.usernameInUse(userDtoToBeSaved.getUsername())) {
            result.rejectValue("username", "duplicate", "Deze gebruikersnaam is al in gebruik");
        }

        if (userDtoToBeSaved.getPassword() == null || !userDtoToBeSaved.getPassword().equals(userDtoToBeSaved.getConfirmPassword())) {
            result.rejectValue("password", "no.match", "Wachtwoorden komen niet overeen");
        }

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.formUser", result);
            redirectAttributes.addFlashAttribute("formUser", userDtoToBeSaved);
            redirectAttributes.addFlashAttribute("formModalHidden", false);
            return "redirect:/user/overview";
        }

        adminUserService.save(userDtoToBeSaved);
        return "redirect:/user/overview";
    }

    @GetMapping("/edit/{username}")
    private String editUser(@PathVariable String username, Model datamodel) {
        NewUserDTO existingUser = adminUserService.getUserDTOByUsername(username);
        existingUser.setOriginalUsername(username);
        datamodel.addAttribute("allUsers", adminUserService.getAllUsers());
        datamodel.addAttribute("formUser", existingUser);
        datamodel.addAttribute("formModalHidden", false);
        return "userOverview";
    }

    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable String username, RedirectAttributes redirectAttributes) {
        try {
            adminUserService.deleteUserByUsername(username);
            redirectAttributes.addFlashAttribute("message", "Gebruiker succesvol verwijderd.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Gebruiker kon niet worden verwijderd.");
        }
        return "redirect:/user/overview";
    }

}