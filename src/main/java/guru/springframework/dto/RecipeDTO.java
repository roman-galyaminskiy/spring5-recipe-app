package guru.springframework.dto;

import guru.springframework.entities.Difficulty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class RecipeDTO {
    private Long id;

    @NotBlank
    private String description;

    @Min(1)
    @Max(999)
    private Integer prepTime;

    @Min(1)
    @Max(999)
    private Integer cookTime;

    @Min(1)
    @Max(999)
    private Integer servings;
    private String source;

    @URL
    private String url;

    @NotBlank
    private String directions;
    private Set<IngredientDTO> ingredients;
    private Difficulty difficulty;
    private NoteDTO note;
    private Set<CategoryDTO> categories;
}
