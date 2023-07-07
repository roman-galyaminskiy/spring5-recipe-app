package guru.springframework.commands;

import guru.springframework.domain.Difficulty;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<IngredientCommand> ingredients;
    private Difficulty difficulty;
    private NoteCommand note;
    private Set<CategoryCommand> categories;
}
