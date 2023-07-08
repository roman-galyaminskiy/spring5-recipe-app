package guru.springframework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public final class UnitOfMeasureDTO {
    private Long id;
    private String description;
}
