package guru.springframework.mappers;

import guru.springframework.dto.RecipeDTO;
import guru.springframework.entities.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    RecipeMapper INSTANCE = Mappers.getMapper( RecipeMapper.class );

    RecipeDTO entityToDto(Recipe entity);

    Recipe dtoToEntity(RecipeDTO dto);
}
