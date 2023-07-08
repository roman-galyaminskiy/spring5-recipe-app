package guru.springframework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
public final class IngredientDTO {
    private Long id;

    private Long recipeId;
    private String description;
    private BigDecimal amount;
    private Long uomId;
}
