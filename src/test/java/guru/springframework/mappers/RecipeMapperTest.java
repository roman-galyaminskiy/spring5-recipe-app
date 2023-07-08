package guru.springframework.mappers;

import guru.springframework.dto.*;
import guru.springframework.entities.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecipeMapperTest {

    RecipeMapper mapper = RecipeMapper.INSTANCE;

    static Ingredient ingredient;
    static IngredientDTO ingredientDTO;
    static Recipe recipe;
    static RecipeDTO recipeDTO;
    static Category category;
    static CategoryDTO categoryDTO;

    static Note note;
    static NoteDTO noteDTO;

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

        // dto
        categoryDTO = CategoryDTO
                .builder()
                .id(1L)
                .description("Test category")
                .build();

        ingredientDTO = IngredientDTO
                .builder()
                .id(1L)
                .amount(BigDecimal.ONE)
                .unitOfMeasure(new UnitOfMeasureDTO(1L, "Test unit"))
                .description("Test ingredient")
                .build();

        noteDTO = NoteDTO
                .builder()
                .id(1L)
                .recipeNotes("Test note")
                .build();

        recipeDTO = RecipeDTO
                .builder()
                .id(1L)
                .ingredients(new HashSet<>())
                .categories(new HashSet<>())
                .description("Test recipe")
                .note(noteDTO)
                .build();

        recipeDTO.getIngredients().add(ingredientDTO);
        recipeDTO.getCategories().add(categoryDTO);
    }

    @Test
    void entityToDto() {
        RecipeDTO result = mapper.entityToDto(recipe);
        assertEquals(result.getId(), recipeDTO.getId());
        assertEquals(
                result.getCategories().stream().findFirst().orElseThrow(RuntimeException::new).getId(),
                recipeDTO.getCategories().stream().findFirst().orElseThrow(RuntimeException::new).getId()
        );
        assertEquals(
                result.getNote().getId(),
                recipeDTO.getNote().getId()
        );
    }

    @Test
    void dtoToEntity() {
        Recipe result = mapper.dtoToEntity(recipeDTO);
        assertEquals(result.getId(), result.getId());
        assertEquals(
                result.getCategories().stream().findFirst().orElseThrow(RuntimeException::new).getId(),
                recipeDTO.getCategories().stream().findFirst().orElseThrow(RuntimeException::new).getId()
        );
        assertEquals(
                result.getNote().getId(),
                recipeDTO.getNote().getId()
        );
    }
}
