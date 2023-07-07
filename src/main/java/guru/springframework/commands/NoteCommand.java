package guru.springframework.commands;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteCommand {

    private Long id;
    // private Recipe recipe;
    private String recipeNotes;
}
