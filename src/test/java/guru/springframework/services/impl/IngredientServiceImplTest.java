package guru.springframework.services.impl;

import guru.springframework.dto.IngredientDTO;
import guru.springframework.entities.Ingredient;
import guru.springframework.entities.Recipe;
import guru.springframework.entities.UnitOfMeasure;
import guru.springframework.mappers.IngredientMapper;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.services.IngredientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {

    IngredientService ingredientService;

    @Mock
    IngredientRepository ingredientRepository;

    @Mock
    IngredientMapper ingredientMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(ingredientRepository, ingredientMapper);
    }

    @Test
    void getDTOById() {
        var ingredient = Ingredient.builder()
                .id(1L)
                .build();

        var ingredientDto = IngredientDTO.builder()
                .id(1L)
                .build();

        when(ingredientRepository.findById(anyLong())).thenReturn(Optional.of(ingredient));
        when(ingredientMapper.entityToDto(any())).thenReturn(ingredientDto);

        IngredientDTO dto = ingredientService.getDTOById(1L);

        assertEquals(dto.getId(), ingredient.getId());
        verify(ingredientRepository, times(1)).findById(anyLong());
    }

    @Test
    void saveDto() {
        //given
        var recipe = Recipe.builder()
                .id(1L)
                .ingredients(new HashSet<>())
                .build();

        var ingredient = Ingredient.builder()
                .id(1L)
                .unitOfMeasure(new UnitOfMeasure(1L, "Piece"))
                .build();

        recipe.addIngredient(ingredient);

        var ingredientDto = IngredientDTO.builder()
                .id(1L)
                .recipeId(1L)
                .uomId(1L)
                .build();

        //when
        when(ingredientRepository.save(any())).thenReturn(ingredient);
        var result = ingredientService.saveDto(ingredientDto);

        //then
        assertEquals(result.getId(), ingredientDto.getId());
        assertEquals(result.getRecipe().getId(), ingredientDto.getRecipeId());
        assertEquals(result.getUnitOfMeasure().getId(), ingredientDto.getUomId());
    }
}