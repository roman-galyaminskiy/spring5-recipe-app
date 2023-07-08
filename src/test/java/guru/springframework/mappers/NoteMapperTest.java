package guru.springframework.mappers;

import guru.springframework.dto.NoteDTO;
import guru.springframework.entities.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoteMapperTest {

    NoteMapper mapper = NoteMapper.INSTANCE;

    static Note note;
    static NoteDTO noteDTO;

    @BeforeEach
    void setUp() {
        note = Note
                .builder()
                .id(1L)
                .recipeNotes("Test note")
                .build();

        noteDTO = NoteDTO
                .builder()
                .id(1L)
                .recipeNotes("Test note")
                .build();
    }

    @Test
    void entityToDto() {
        NoteDTO result = mapper.entityToDto(note);
        assertEquals(result.getId(), note.getId());
        assertEquals(result.getRecipeNotes(), note.getRecipeNotes());
    }

    @Test
    void dtoToEntity() {
        Note result = mapper.dtoToEntity(noteDTO);
        assertEquals(result.getId(), noteDTO.getId());
        assertEquals(result.getRecipeNotes(), noteDTO.getRecipeNotes());
    }
}