package guru.springframework.controllers;

import guru.springframework.dto.IngredientDTO;
import guru.springframework.dto.RecipeDTO;
import guru.springframework.entities.Ingredient;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;

    private final IngredientService ingredientService;

    private final UnitOfMeasureService uomService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService uomService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.uomService = uomService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    String listRecipeIngredients(@PathVariable(name = "recipeId") Long id, Model model) {
        model.addAttribute("recipe", recipeService.getDtoById(id));
        return "ingredient/list";
    }

    @GetMapping("/ingredient/{ingredientId}/show")
    String showIngredient(@PathVariable(name = "ingredientId") Long id, Model model) {
        model.addAttribute("ingredient", ingredientService.getDTOById(id));
        return "ingredient/show";
    }

    @GetMapping("/ingredient/{ingredientId}/edit")
    String editIngredient(@PathVariable(name = "ingredientId") Long id, Model model) {
        model.addAttribute("ingredient", ingredientService.getDTOById(id));
        model.addAttribute("uomList", uomService.getDTOList());
        return "ingredient/form";
    }

    @GetMapping("/ingredient/{ingredientId}/delete")
    String deleteIngredient(@PathVariable(name = "ingredientId") Long id, Model model) {
        Long recipeId = ingredientService.getDTOById(id).getRecipeId();
        ingredientService.deleteById(id);
        model.addAttribute("recipe", recipeService.getDtoById(recipeId));
        return "ingredient/list";
    }

    @PostMapping(path = "/ingredient")
    String saveOrUpdate(@ModelAttribute IngredientDTO dto) {
        log.info("saveOrUpdate");
        Ingredient ingredient = ingredientService.saveDto(dto);
        log.info("redirect:/ingredient/" + ingredient.getId() + "/show");
        return "redirect:/ingredient/" + ingredient.getId() + "/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/new")
    String newIngredient(@PathVariable(name = "recipeId") Long id, Model model) {
        var ingredientDTO = IngredientDTO.builder()
                .recipeId(id)
                .build();

        model.addAttribute("ingredient", ingredientDTO);
        model.addAttribute("uomList", uomService.getDTOList());
        return "ingredient/form";
    }
}
