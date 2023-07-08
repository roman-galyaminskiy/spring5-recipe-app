package guru.springframework.dto;

import guru.springframework.entities.Difficulty;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class RecipeDTO {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<IngredientDTO> ingredients;
    private Difficulty difficulty;
    private NoteDTO note;
    private Set<CategoryDTO> categories;
}
