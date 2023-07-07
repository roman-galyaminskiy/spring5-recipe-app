package guru.springframework.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
public final class IngredientCommand {
    private Long id;
    private String description;
    private BigDecimal amount;
    // private RecipeCommand recipe;
    private UnitOfMeasureCommand unitOfMeasure;
}
