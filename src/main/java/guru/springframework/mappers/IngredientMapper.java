package guru.springframework.mappers;

import guru.springframework.dto.IngredientDTO;
import guru.springframework.entities.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    IngredientMapper INSTANCE = Mappers.getMapper( IngredientMapper.class );

    IngredientDTO entityToDto(Ingredient entity);

    Ingredient dtoToEntity(IngredientDTO dto);
}
