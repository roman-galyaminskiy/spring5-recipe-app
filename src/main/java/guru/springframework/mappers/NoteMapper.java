package guru.springframework.mappers;

import guru.springframework.dto.NoteDTO;
import guru.springframework.entities.Note;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    NoteMapper INSTANCE = Mappers.getMapper( NoteMapper.class );

    NoteDTO entityToDto(Note entity);

    Note dtoToEntity(NoteDTO dto);
}
