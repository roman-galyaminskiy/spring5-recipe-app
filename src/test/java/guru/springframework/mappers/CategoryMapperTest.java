package guru.springframework.mappers;

import guru.springframework.dto.CategoryDTO;
import guru.springframework.entities.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryMapperTest {

    CategoryMapper mapper = CategoryMapper.INSTANCE;
    static Category category;
    static CategoryDTO categoryDTO;
    @BeforeEach
    void setUp() {
        // entites
        category = Category
                .builder()
                .id(1L)
                .description("Test category")
                .build();

        // dto
        categoryDTO = CategoryDTO
                .builder()
                .id(1L)
                .description("Test category")
                .build();
    }

    @Test
    void entityToDto() {
        CategoryDTO result = mapper.entityToDto(category);
        assertEquals(result.getId(), categoryDTO.getId());
        assertEquals(result.getDescription(), categoryDTO.getDescription());
    }

    @Test
    void dtoToEntity() {
        Category result = mapper.dtoToEntity(categoryDTO);
        assertEquals(result.getId(), category.getId());
        assertEquals(result.getDescription(), category.getDescription());
    }
}