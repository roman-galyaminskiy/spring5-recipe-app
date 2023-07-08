package guru.springframework.services;

import guru.springframework.dto.RecipeDTO;
import guru.springframework.entities.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> findAll();

    Recipe findById(Long id);
    Recipe saveRecipeDto(RecipeDTO dto);

    RecipeDTO getDtoById(Long id);

    void deleteById(Long id);
}
