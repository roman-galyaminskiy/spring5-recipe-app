package guru.springframework.mappers;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    IngredientMapper INSTANCE = Mappers.getMapper( IngredientMapper.class );

    IngredientCommand entityToCommand(Ingredient entity);

    Ingredient commandToEntity(IngredientCommand command);
}
