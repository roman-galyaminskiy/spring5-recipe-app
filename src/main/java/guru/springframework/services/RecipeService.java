package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> findAll();

    Recipe findById(Long id);
    Recipe saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(Long id);
}
