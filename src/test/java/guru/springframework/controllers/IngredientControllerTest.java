package guru.springframework.controllers;

import guru.springframework.dto.IngredientDTO;
import guru.springframework.dto.RecipeDTO;
import guru.springframework.dto.UnitOfMeasureDTO;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(IngredientController.class)
class IngredientControllerTest {

    @MockBean
    RecipeService recipeService;

    @MockBean
    IngredientService ingredientService;

    @MockBean
    UnitOfMeasureService uomService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testListIngredients() throws Exception {
        RecipeDTO recipeDTO = RecipeDTO.builder()
                .id(1L)
                .description("test")
                .build();

        when(recipeService.getDtoById(anyLong())).thenReturn(recipeDTO);

        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("ingredient/list"));

        verify(recipeService, times(1)).getDtoById(anyLong());
    }

    @Test
    public void testShowIngredients() throws Exception {
        long ingredientId = 1L;
        var dto = IngredientDTO.builder()
                .id(ingredientId)
                .amount(BigDecimal.ONE)
                .uomId(1L)
                .build();

        when(ingredientService.getDTOById(anyLong())).thenReturn(dto);

        mockMvc.perform(get("/ingredient/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("ingredient/show"))
                .andExpect(model().attribute("ingredient", Matchers.hasProperty("id", is(ingredientId))));

        verify(ingredientService, times(1)).getDTOById(anyLong());
    }

    @Test
    void testEditIngredient() throws Exception {
        var uomDto1 = new UnitOfMeasureDTO(1L, "Teaspoon");
        var uomDto2 = new UnitOfMeasureDTO(2L, "Peace");

        long ingredientId = 1L;
        var ingredientDTO = IngredientDTO.builder()
                .id(ingredientId)
                .amount(BigDecimal.ONE)
                .uomId(1L)
                .build();

        List<UnitOfMeasureDTO> uomDtoList = List.of(uomDto1, uomDto2);
        when(ingredientService.getDTOById(anyLong())).thenReturn(ingredientDTO);
        when(uomService.getDTOList()).thenReturn(uomDtoList);

        mockMvc.perform(get("/ingredient/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("ingredient/form"))
                .andExpect(model().attribute("ingredient", Matchers.hasProperty("id", is(ingredientId))))
                .andExpect(model().attribute("uomList", hasSize(2)));

        verify(ingredientService, times(1)).getDTOById(anyLong());
        verify(uomService, times(1)).getDTOList();

    }

    @Test
    void testDeleteIngredient() throws Exception {
        // for "ingredient/list"
        long recipeId = 1L;
        long ingredientId = 1L;

        var ingredientDTO = IngredientDTO.builder()
                .id(ingredientId)
                .recipeId(recipeId)
                .amount(BigDecimal.ONE)
                .uomId(1L)
                .build();

        when(ingredientService.getDTOById(anyLong())).thenReturn(ingredientDTO);

        RecipeDTO recipeDTO = RecipeDTO.builder()
                .id(recipeId)
                .description("test")
                .build();

        when(recipeService.getDtoById(anyLong())).thenReturn(recipeDTO);

        mockMvc.perform(get("/ingredient/1/delete"))
                .andExpect(status().isOk())
                .andExpect(view().name("ingredient/list"));

        verify(ingredientService, times(1)).deleteById(anyLong());
    }
}