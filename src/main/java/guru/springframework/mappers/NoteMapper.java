package guru.springframework.mappers;

import guru.springframework.commands.NoteCommand;
import guru.springframework.domain.Note;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    NoteMapper INSTANCE = Mappers.getMapper( NoteMapper.class );

    NoteCommand entityToCommand(Note entity);

    Note commandToEntity(NoteCommand command);
}
