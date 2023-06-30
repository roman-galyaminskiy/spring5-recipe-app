package guru.springframework.mappers;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper( CategoryMapper.class );

    CategoryCommand entityToCommand(Category entity);

    Category commandToEntity(CategoryCommand command);
}
