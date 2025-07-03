package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.controller;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.Ingredient;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories.IngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Robyn Blignaut, Bas Folkers
 * Handle all requests related to ingredients for recipes
 */

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientRepository ingredientRepository;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    private String setupIngredientOverview(Model datamodel, Ingredient formIngredient, boolean formModalHidden) {
        datamodel.addAttribute("allIngredients", ingredientRepository.findAll());
        datamodel.addAttribute("formIngredient", formIngredient);
        datamodel.addAttribute("formModalHidden", formModalHidden);

        return "ingredientOverview";
    }

//    private String setupIngredientDetail(Model datamodel, Ingredient ingredientToShow, Ingredient formIngredient, boolean formModalHidden) {
//        datamodel.addAttribute("ingredient", ingredientToShow);
//        datamodel.addAttribute("formIngredient", formIngredient);
//        datamodel.addAttribute("formModalHidden", formModalHidden);
//
//        return "ingredientDetails";
//    }

    @GetMapping("/overview")
    public String showIngredientOverview(Model datamodel) {
        datamodel.addAttribute("allIngredients", ingredientRepository.findAll());
        return setupIngredientOverview(datamodel, new Ingredient(), true);
    }

    @GetMapping("/new")
    private String newIngredient(Model datamodel) {
        datamodel.addAttribute("ingredientForm", new Ingredient());
        return "ingredientForm";
    }

    @GetMapping("/edit/{ingredientId}")
    private String editIngredient(@PathVariable("ingredientId") Long ingredientId, Model datamodel) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(ingredientId);
        if (ingredientOptional.isPresent()) {
            datamodel.addAttribute("ingredientForm", ingredientOptional.get());
        }
        return "ingredientForm";
    }

    @PostMapping("/save")
    private String saveOrUpdateIngredient(@ModelAttribute("ingredientForm") Ingredient ingredientToBeSaved,
                                          BindingResult result,
                                          Model datamodel) {

        if (result.hasErrors() && ingredientToBeSaved.getIngredientId() != null) {
            Ingredient originalIngredientDetails = ingredientRepository
                                                    .findById(ingredientToBeSaved.getIngredientId())
                                                    .orElse(new Ingredient());

//            return setupIngredientDetail(datamodel, originalIngredientDetails, ingredientToBeSaved, false);
        } else if (result.hasErrors()) {
            return setupIngredientOverview(datamodel, ingredientToBeSaved, false);
        }
        ingredientRepository.save(ingredientToBeSaved);
        return "redirect:/ingredient/overview";
    }

    private void checkIngredientNameIsUnique(Ingredient ingredientToBeSaved, BindingResult result) {
        Optional<Ingredient> sameName = ingredientRepository.findByIngredientName(ingredientToBeSaved.getIngredientName());
        if (sameName.isPresent() && !sameName.get().getIngredientId().equals(ingredientToBeSaved.getIngredientId())) {
            result.addError(new FieldError("ingredient", "name", "this name is already in use"));
        }
    }

    @GetMapping("/delete/{ingredientName}")
    private String deleteIngredient(@PathVariable("ingredientName") String name) {
        ingredientRepository.findByIngredientName(name).ifPresent(ingredientRepository::delete);
        return "redirect:/ingredient/overview";
    }
}
