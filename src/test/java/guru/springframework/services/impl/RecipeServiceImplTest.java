package guru.springframework.services.impl;

import guru.springframework.entities.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);
    }
    @Test
    void findAll() {

        Set<Recipe> mockRecipes = new HashSet<>();
        mockRecipes.add(new Recipe());

        when(recipeRepository.findAll()).thenReturn(mockRecipes);

        Set<Recipe> recipes = recipeService.findAll();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void testDeleteById() {
        long id = 1L;
        recipeService.deleteById(id);

        // no when since method returns null

        verify(recipeRepository, times(1)).deleteById(id);
    }
}