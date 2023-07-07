package guru.springframework.services.impl;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.mappers.RecipeMapper;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    private final RecipeMapper mapper = RecipeMapper.INSTANCE;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> findAll() {
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().forEach(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Recipe not found!"));
    }

    @Override
    public Recipe saveRecipeCommand(RecipeCommand command) {
        return recipeRepository.save(mapper.commandToEntity(command));
    }

    @Override
    public RecipeCommand findCommandById(Long id) {
        return recipeRepository.findById(id)
                .map(recipe -> RecipeMapper.INSTANCE.entityToCommand(recipe))
                .orElseThrow(() -> new RuntimeException("Recipe not found!"));
    }
}
;