package guru.springframework.mappers;

import guru.springframework.dto.IngredientDTO;
import guru.springframework.dto.UnitOfMeasureDTO;
import guru.springframework.entities.Ingredient;
import guru.springframework.entities.Recipe;
import guru.springframework.entities.UnitOfMeasure;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;
import guru.springframework.services.impl.RecipeServiceImpl;
import guru.springframework.services.impl.UnitOfMeasureServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngredientMapperTest {

    IngredientMapper ingredientMapper;

    Recipe recipe;

    Ingredient ingredient;

    IngredientDTO ingredientDTO;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    @Mock
    RecipeService recipeService;

    @BeforeEach
    void setUp() {
        ingredientMapper = new IngredientMapperImpl(recipeService, unitOfMeasureService);

        recipe = Recipe.builder()
                .id(1L)
                .ingredients(new HashSet<>())
                .build();

        ingredient = Ingredient
                .builder()
                .id(1L)
                .amount(BigDecimal.ONE)
                .unitOfMeasure(new UnitOfMeasure(1L, "Test unit"))
                .description("Test ingredient")
                .build();

        recipe.addIngredient(ingredient);

        ingredientDTO = IngredientDTO
                .builder()
                .id(1L)
                .amount(BigDecimal.ONE)
                .uomId(1L)
                .recipeId(1L)
                .description("Test ingredient")
                .build();
    }

    @Test
    void entityToDto() {
        IngredientDTO result = ingredientMapper.entityToDto(ingredient);
        assertEquals(result.getId(), ingredientDTO.getId());
    }

    @Test
    void dtoToEntity() {
        var recipe = Recipe.builder()
                .id(1L)
                .build();

        var uom = UnitOfMeasure.builder()
                .id(1L)
                .build();

        when(recipeService.findById(anyLong())).thenReturn(recipe);
        when(unitOfMeasureService.findById(anyLong())).thenReturn(uom);

        Ingredient result = ingredientMapper.dtoToEntity(ingredientDTO);
        assertEquals(result.getId(), ingredient.getId());
        assertEquals(result.getRecipe().getId(), ingredient.getRecipe().getId());
    }
}