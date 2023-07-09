package guru.springframework.controllers;

import guru.springframework.dto.RecipeDTO;
import guru.springframework.entities.Recipe;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

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
        model.addAttribute("recipe", new RecipeDTO());
        return "/recipe/form";
    }

    @GetMapping({"/{recipeId}/update"})
    String updateRecipe(@PathVariable(name = "recipeId") Long id, Model model) {
        var dto = recipeService.getDtoById(id);
        log.info(dto.toString());
        model.addAttribute("recipe", dto);
        return "/recipe/form";
    }

    @PostMapping(path = {"/", ""})
    String saveOrUpdate(@ModelAttribute RecipeDTO dto) {
        log.info("saveOrUpdate");
        Recipe recipe = recipeService.saveRecipeDto(dto);
        log.info("redirect:/recipe/" + recipe.getId() + "/show");
        return "redirect:/recipe/" + recipe.getId() + "/show";
    }

    @GetMapping({"/{recipeId}/delete"})
    String deleteRecipe(@PathVariable(name = "recipeId") Long id) {
        recipeService.deleteById(id);
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView notFoundHandler(Exception e) {
        log.error("Handling recipe not found exception");
        log.error(e.getMessage());
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("error");
        modelAndView.addObject("title", "404 Not found");
        modelAndView.addObject("exception", e);
        return modelAndView;
    }
}
