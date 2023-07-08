package guru.springframework.controllers;

import guru.springframework.dto.RecipeDTO;
import guru.springframework.entities.Recipe;
import guru.springframework.services.RecipeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecipeController.class)
class RecipeControllerTest {

    @MockBean
    RecipeService recipeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getRecipeById() throws Exception {
        var recipeId = 1L;
        var recipe = new Recipe();
        recipe.setId(recipeId);

        when(recipeService.findById(recipeId)).thenReturn(recipe);

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/show"))
                .andExpect(model().attribute("recipe", Matchers.hasProperty("id", is(recipeId))));
    }

    @Test
    public void getNewRecipeForm() throws Exception {
        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/form"))
                .andExpect(model().attribute("recipe", Matchers.hasProperty("id", is(nullValue()))));
    }

    @Test
    public void getExistingRecipeForm() throws Exception {
        var recipeId = 1L;
        var dto = new RecipeDTO();
        dto.setId(recipeId);

        when(recipeService.getDtoById(recipeId)).thenReturn(dto);

        mockMvc.perform(get("/recipe/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/form"))
                .andExpect(model().attribute("recipe", Matchers.hasProperty("id", is(recipeId))));
    }

    @Test
    public void postRecipeForm() throws Exception {
        var recipeId = 1L;
        var recipe = new Recipe();
        recipe.setId(recipeId);

        when(recipeService.saveRecipeDto(any())).thenReturn(recipe);

        mockMvc.perform(post("/recipe")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "1")
                        .param("description", "test")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/" + recipeId + "/show"));
    }

    @Test
    public void deleteRecipe() throws Exception {
        var recipeId = 1L;
        var dto = new RecipeDTO();
        dto.setId(recipeId);

        mockMvc.perform(get("/recipe/" + recipeId + "/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(recipeService, times(1)).deleteById(anyLong());
    }

}