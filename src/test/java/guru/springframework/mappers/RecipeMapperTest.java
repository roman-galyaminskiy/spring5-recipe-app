package guru.springframework.mappers;

import guru.springframework.commands.*;
import guru.springframework.domain.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecipeMapperTest {

    RecipeMapper mapper = RecipeMapper.INSTANCE;

    static Ingredient ingredient;
    static IngredientCommand ingredientCommand;
    static Recipe recipe;
    static RecipeCommand recipeCommand;
    static Category category;
    static CategoryCommand categoryCommand;

    static Note note;
    static NoteCommand noteCommand;

    @BeforeAll
    static void setUp() {
        // entites
        category = Category
                .builder()
                .id(1L)
                .description("Test category")
                .build();

        ingredient = Ingredient
                .builder()
                .id(1L)
                .amount(BigDecimal.ONE)
                .unitOfMeasure(new UnitOfMeasure(1L, "Test unit"))
                .description("Test ingredient")
                .build();

        note = Note
                .builder()
                .id(1L)
                .recipeNotes("Test note")
                .build();

        recipe = Recipe
                .builder()
                .id(1L)
                .ingredients(new HashSet<>())
                .categories(new HashSet<>())
                .description("Test recipe")
                .note(note)
                .build();

        recipe.getIngredients().add(ingredient);
        recipe.getCategories().add(category);

        // commands
        categoryCommand = CategoryCommand
                .builder()
                .id(1L)
                .description("Test category")
                .build();

        ingredientCommand = IngredientCommand
                .builder()
                .id(1L)
                .amount(BigDecimal.ONE)
                .unitOfMeasure(new UnitOfMeasureCommand(1L, "Test unit"))
                .description("Test ingredient")
                .build();

        noteCommand = NoteCommand
                .builder()
                .id(1L)
                .recipeNotes("Test note")
                .build();

        recipeCommand = RecipeCommand
                .builder()
                .id(1L)
                .ingredients(new HashSet<>())
                .categories(new HashSet<>())
                .description("Test recipe")
                .note(noteCommand)
                .build();

        recipeCommand.getIngredients().add(ingredientCommand);
        recipeCommand.getCategories().add(categoryCommand);
    }

    @Test
    void entityToCommand() {
        RecipeCommand result = mapper.entityToCommand(recipe);
        assertEquals(result.getId(), recipeCommand.getId());
        assertEquals(
                result.getCategories().stream().findFirst().orElseThrow(RuntimeException::new).getId(),
                recipeCommand.getCategories().stream().findFirst().orElseThrow(RuntimeException::new).getId()
        );
        assertEquals(
                result.getNote().getId(),
                recipeCommand.getNote().getId()
        );
    }

    @Test
    void commandToEntity() {
        Recipe result = mapper.commandToEntity(recipeCommand);
        assertEquals(result.getId(), result.getId());
        assertEquals(
                result.getCategories().stream().findFirst().orElseThrow(RuntimeException::new).getId(),
                recipeCommand.getCategories().stream().findFirst().orElseThrow(RuntimeException::new).getId()
        );
        assertEquals(
                result.getNote().getId(),
                recipeCommand.getNote().getId()
        );
    }
}
