package guru.springframework.mappers;

import guru.springframework.dto.UnitOfMeasureDTO;
import guru.springframework.entities.UnitOfMeasure;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UnitOfMeasureMapper {

    UnitOfMeasureMapper INSTANCE = Mappers.getMapper( UnitOfMeasureMapper.class );

    UnitOfMeasureDTO entityToDto(UnitOfMeasure entity);

    UnitOfMeasure dtoToEntity(UnitOfMeasureDTO dto);
}
