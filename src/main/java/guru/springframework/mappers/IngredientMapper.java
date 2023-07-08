package guru.springframework.mappers;

import guru.springframework.dto.IngredientDTO;
import guru.springframework.entities.Ingredient;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {RecipeService.class, UnitOfMeasureService.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface  IngredientMapper {

    @Mapping(target="recipeId", source="recipe.id")
    @Mapping(target="uomId", source="unitOfMeasure.id")
    IngredientDTO entityToDto(Ingredient entity);

    @Mapping(source = "recipeId", target = "recipe")
    @Mapping(source = "uomId", target = "unitOfMeasure")
    Ingredient dtoToEntity(IngredientDTO dto);
}
