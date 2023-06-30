package guru.springframework.mappers;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryMapperTest {

    CategoryMapper mapper = CategoryMapper.INSTANCE;
    static Category category;
    static CategoryCommand categoryCommand;
    @BeforeEach
    void setUp() {
        // entites
        category = Category
                .builder()
                .id(1L)
                .description("Test category")
                .build();

        // commands
        categoryCommand = CategoryCommand
                .builder()
                .id(1L)
                .description("Test category")
                .build();
    }

    @Test
    void entityToCommand() {
        CategoryCommand result = mapper.entityToCommand(category);
        assertEquals(result.getId(), categoryCommand.getId());
        assertEquals(result.getDescription(), categoryCommand.getDescription());
    }

    @Test
    void commandToEntity() {
        Category result = mapper.commandToEntity(categoryCommand);
        assertEquals(result.getId(), category.getId());
        assertEquals(result.getDescription(), category.getDescription());
    }
}