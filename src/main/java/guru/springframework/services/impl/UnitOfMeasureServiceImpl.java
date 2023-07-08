package guru.springframework.services.impl;

import guru.springframework.dto.UnitOfMeasureDTO;
import guru.springframework.entities.UnitOfMeasure;
import guru.springframework.mappers.UnitOfMeasureMapper;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository uomRepository;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository uomRepository) {
        this.uomRepository = uomRepository;
    }

    @Override
    public UnitOfMeasure findById(Long id) {
        return uomRepository.findById(id).orElseThrow(() -> new RuntimeException("unit of measure not found"));
    }

    @Override
    public List<UnitOfMeasureDTO> getDTOList() {
        return StreamSupport.stream(uomRepository.findAll().spliterator(), false)
                .map(UnitOfMeasureMapper.INSTANCE::entityToDto)
                .toList();
    }
}
