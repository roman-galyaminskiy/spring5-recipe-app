package guru.springframework.services;

import guru.springframework.dto.IngredientDTO;
import guru.springframework.entities.Ingredient;

public interface IngredientService {

    Ingredient findByid(Long id);

    IngredientDTO getDTOById(Long id);

    void deleteById(Long id);

    Ingredient saveDto(IngredientDTO dto);
}
