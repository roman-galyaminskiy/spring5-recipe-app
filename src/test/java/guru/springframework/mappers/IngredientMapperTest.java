package guru.springframework.mappers;

import guru.springframework.dto.IngredientDTO;
import guru.springframework.dto.UnitOfMeasureDTO;
import guru.springframework.entities.Ingredient;
import guru.springframework.entities.UnitOfMeasure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientMapperTest {

    IngredientMapper mapper = IngredientMapper.INSTANCE;

    static Ingredient ingredient;

    static IngredientDTO ingredientDTO;

    @BeforeAll
    static void setUp() {
        ingredient = Ingredient
                .builder()
                .id(1L)
                .amount(BigDecimal.ONE)
                .unitOfMeasure(new UnitOfMeasure(1L, "Test unit"))
                .description("Test ingredient")
                .build();

        ingredientDTO = IngredientDTO
                .builder()
                .id(1L)
                .amount(BigDecimal.ONE)
                .unitOfMeasure(new UnitOfMeasureDTO(1L, "Test unit"))
                .description("Test ingredient")
                .build();
    }

    @Test
    void entityToDto() {
        IngredientDTO result = mapper.entityToDto(ingredient);
        assertEquals(result.getId(), ingredientDTO.getId());
    }

    @Test
    void dtoToEntity() {
        Ingredient result = mapper.dtoToEntity(ingredientDTO);
        assertEquals(result.getId(), ingredient.getId());
    }
}