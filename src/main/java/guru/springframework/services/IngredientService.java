package guru.springframework.services;

import guru.springframework.dto.IngredientDTO;
import guru.springframework.dto.RecipeDTO;
import guru.springframework.entities.Ingredient;
import guru.springframework.entities.Recipe;

import java.util.Set;

public interface IngredientService {

    Ingredient findByid(Long id);

    IngredientDTO getDTOById(Long id);

    void deleteById(Long id);

    Ingredient saveDto(IngredientDTO dto);
}
