package guru.springframework.mappers;

import guru.springframework.dto.UnitOfMeasureDTO;
import guru.springframework.entities.UnitOfMeasure;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UnitOfMeasureMapperTest {

    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "description";
    private static final UnitOfMeasure uom = new UnitOfMeasure(ID_VALUE, DESCRIPTION);
    private static final UnitOfMeasureDTO uomDto = new UnitOfMeasureDTO(ID_VALUE, DESCRIPTION);

    UnitOfMeasureMapper mapper = UnitOfMeasureMapper.INSTANCE;

    @Test
    void entityToDto() {
        UnitOfMeasureDTO result = mapper.entityToDto(uom);
        assertEquals(result.getId(), uomDto.getId());
        assertEquals(result.getDescription(), uomDto.getDescription());
    }

    @Test
    void dtoToEntity() {
        UnitOfMeasure result = mapper.dtoToEntity(uomDto);
        assertEquals(result.getId(), uom.getId());
        assertEquals(result.getDescription(), uom.getDescription());
    }

    @Test
    void nullDto() {
        UnitOfMeasure result = mapper.dtoToEntity(null);
        assertNull(result);
    }

    @Test
    void nullEntity() {
        UnitOfMeasureDTO result = mapper.entityToDto(null);
        assertNull(result);
    }
}