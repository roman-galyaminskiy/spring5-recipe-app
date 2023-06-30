package guru.springframework.mappers;

import guru.springframework.commands.NoteCommand;
import guru.springframework.domain.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoteMapperTest {

    NoteMapper mapper = NoteMapper.INSTANCE;

    static Note note;
    static NoteCommand noteCommand;

    @BeforeEach
    void setUp() {
        note = Note
                .builder()
                .id(1L)
                .recipeNotes("Test note")
                .build();

        noteCommand = NoteCommand
                .builder()
                .id(1L)
                .recipeNotes("Test note")
                .build();
    }

    @Test
    void entityToCommand() {
        NoteCommand result = mapper.entityToCommand(note);
        assertEquals(result.getId(), note.getId());
        assertEquals(result.getRecipeNotes(), note.getRecipeNotes());
    }

    @Test
    void commandToEntity() {
        Note result = mapper.commandToEntity(noteCommand);
        assertEquals(result.getId(), noteCommand.getId());
        assertEquals(result.getRecipeNotes(), noteCommand.getRecipeNotes());
    }
}