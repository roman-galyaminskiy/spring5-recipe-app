package guru.springframework.mappers;

import guru.springframework.dto.CategoryDTO;
import guru.springframework.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper( CategoryMapper.class );

    CategoryDTO entityToDto(Category entity);

    Category dtoToEntity(CategoryDTO dto);
}
