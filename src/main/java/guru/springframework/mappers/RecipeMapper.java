package guru.springframework.mappers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    RecipeMapper INSTANCE = Mappers.getMapper( RecipeMapper.class );

    RecipeCommand entityToCommand(Recipe entity);

    Recipe commandToEntity(RecipeCommand command);
}
