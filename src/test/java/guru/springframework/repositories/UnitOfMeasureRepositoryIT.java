package guru.springframework.repositories;

import guru.springframework.entities.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findTeaspoonByDescription() {
        Optional<UnitOfMeasure> optionalUom = unitOfMeasureRepository.findByDescription("Teaspoon");

        assertEquals("Teaspoon", optionalUom.get().getDescription());
    }

    @Test
    void findCupByDescription() {
        Optional<UnitOfMeasure> optionalUom = unitOfMeasureRepository.findByDescription("Cup");

        assertEquals("Cup", optionalUom.get().getDescription());
    }
}