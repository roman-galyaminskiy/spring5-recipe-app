package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"/{recipeId}/show"})
    String showRecipe(@PathVariable(name = "recipeId") Long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "/recipe/show";
    }

    @GetMapping("/new")
    String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "/recipe/form";
    }

    @GetMapping({"/{recipeId}/update"})
    String updateRecipe(@PathVariable(name = "recipeId") Long id, Model model) {
        var command = recipeService.findCommandById(id);
        log.info(command.toString());
        model.addAttribute("recipe", command);
        return "/recipe/form";
    }

    @PostMapping(path = {"/", ""})
    String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        log.info("saveOrUpdate");
        Recipe recipe = recipeService.saveRecipeCommand(command);
        log.info("redirect:/recipe/" + recipe.getId() + "/show");
        return "redirect:/recipe/" + recipe.getId() + "/show";
    }
}
