package guru.springframework.controllers;

import guru.springframework.dto.RecipeDTO;
import guru.springframework.entities.Note;
import guru.springframework.entities.Recipe;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecipeController.class)
class RecipeControllerTest {

    public static final long RECIPE_ID = 1L;
    @MockBean
    RecipeService recipeService;

    @MockBean
    IngredientService ingredientService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetRecipeById() throws Exception {
        var recipeId = 1L;

        var recipe = Recipe.builder().id(recipeId).note(Note.builder().recipeNotes("test").build()).build();

        when(recipeService.findById(recipeId)).thenReturn(recipe);

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/show"))
                .andExpect(model().attribute("recipe", Matchers.hasProperty("id", is(recipeId))));
    }

    @Test
    public void testGetNewRecipeForm() throws Exception {
        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/form"))
                .andExpect(model().attribute("recipe", Matchers.hasProperty("id", is(nullValue()))));
    }

    @Test
    public void testGetExistingRecipeForm() throws Exception {
        var dto = RecipeDTO.builder()
                .id(RECIPE_ID)
                .build();

        when(recipeService.getDtoById(RECIPE_ID)).thenReturn(dto);

        mockMvc.perform(get("/recipe/" + RECIPE_ID + "/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/form"))
                .andExpect(model().attribute("recipe", Matchers.hasProperty("id", is(RECIPE_ID))));
    }

    @Test
    public void testPostRecipeFormSuccess() throws Exception {
        var recipe = Recipe.builder()
                .id(RECIPE_ID)
                .build();

        when(recipeService.saveRecipeDto(any())).thenReturn(recipe);

        mockMvc.perform(post("/recipe")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "1")
                        .param("prepTime", "1")
                        .param("cookTime", "1")
                        .param("servings", "1")
                        .param("directions", "1")
                        .param("description", "test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/" + RECIPE_ID + "/show"));
    }

    @Test
    public void testPostRecipeFormInvalidRecipe() throws Exception {
        var recipe = new Recipe();
        recipe.setId(RECIPE_ID);

        when(recipeService.saveRecipeDto(any())).thenReturn(recipe);

        mockMvc.perform(post("/recipe")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "1")
                        .param("description", "test"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/form"));
    }

    @Test
    public void testDeleteRecipe() throws Exception {
        var recipeId = RECIPE_ID;
        var dto = new RecipeDTO();
        dto.setId(recipeId);

        mockMvc.perform(get("/recipe/" + recipeId + "/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(recipeService, times(1)).deleteById(anyLong());
    }

    @Test
    public void testRecipeNotFound() throws Exception {
        when(recipeService.findById(any())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("error"));
    }

    @Test
    public void testRecipeIdNumberFormatException() throws Exception {
        when(recipeService.findById(any())).thenThrow(MethodArgumentTypeMismatchException.class);

        mockMvc.perform(get("/recipe/1234/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("error"));
    }


}