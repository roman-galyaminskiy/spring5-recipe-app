package guru.springframework.services.impl;

import guru.springframework.dto.IngredientDTO;
import guru.springframework.entities.Ingredient;
import guru.springframework.mappers.IngredientMapper;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.services.IngredientService;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    private final IngredientMapper ingredientMapper;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientMapper ingredientMapper) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientMapper = ingredientMapper;
    }

    @Override
    public Ingredient findByid(Long id) {
        return ingredientRepository.findById(id).orElseThrow(() -> new RuntimeException("Ingredient not found!"));
    }

    @Override
    public IngredientDTO getDTOById(Long id) {
        return ingredientRepository.findById(id)
                .map(ingredientMapper::entityToDto)
                .orElseThrow(() -> new RuntimeException("Ingredient not found!"));
    }

    @Override
    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }

    @Override
    public Ingredient saveDto(IngredientDTO dto) {
        return ingredientRepository.save(ingredientMapper.dtoToEntity(dto));
    }
}
