package guru.springframework.services;

import guru.springframework.dto.UnitOfMeasureDTO;
import guru.springframework.entities.UnitOfMeasure;

import java.util.List;

public interface UnitOfMeasureService {
    UnitOfMeasure findById(Long id);
    List<UnitOfMeasureDTO> getDTOList();
}
