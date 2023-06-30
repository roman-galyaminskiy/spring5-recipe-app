package guru.springframework.mappers;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientMapperTest {

    IngredientMapper mapper = IngredientMapper.INSTANCE;

    static Ingredient ingredient;

    static IngredientCommand ingredientCommand;

    @BeforeAll
    static void setUp() {
        ingredient = Ingredient
                .builder()
                .id(1L)
                .amount(BigDecimal.ONE)
                .unitOfMeasure(new UnitOfMeasure(1L, "Test unit"))
                .description("Test ingredient")
                .build();

        ingredientCommand = IngredientCommand
                .builder()
                .id(1L)
                .amount(BigDecimal.ONE)
                .unitOfMeasure(new UnitOfMeasureCommand(1L, "Test unit"))
                .description("Test ingredient")
                .build();
    }

    @Test
    void entityToCommand() {
        IngredientCommand result = mapper.entityToCommand(ingredient);
        assertEquals(result.getId(), ingredientCommand.getId());
    }

    @Test
    void commandToEntity() {
        Ingredient result = mapper.commandToEntity(ingredientCommand);
        assertEquals(result.getId(), ingredient.getId());
    }
}