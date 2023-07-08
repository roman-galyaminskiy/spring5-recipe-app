package guru.springframework.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {

    private Long id;
    private String recipeNotes;
}
