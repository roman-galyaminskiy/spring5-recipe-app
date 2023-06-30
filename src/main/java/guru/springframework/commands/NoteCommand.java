package guru.springframework.commands;

import guru.springframework.domain.Recipe;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteCommand {

    private Long id;
    private Recipe recipe;
    private String recipeNotes;
}
