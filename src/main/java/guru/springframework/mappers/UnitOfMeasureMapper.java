package guru.springframework.mappers;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UnitOfMeasureMapper {

    UnitOfMeasureMapper INSTANCE = Mappers.getMapper( UnitOfMeasureMapper.class );

    UnitOfMeasureCommand entityToCommand(UnitOfMeasure entity);

    UnitOfMeasure commandToEntity(UnitOfMeasureCommand command);
}
