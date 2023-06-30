package guru.springframework.mappers;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UnitOfMeasureMapperTest {

    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "description";
    private static final UnitOfMeasure uom = new UnitOfMeasure(ID_VALUE, DESCRIPTION);
    private static final UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand(ID_VALUE, DESCRIPTION);

    UnitOfMeasureMapper mapper = UnitOfMeasureMapper.INSTANCE;

    @Test
    void entityToCommand() {
        UnitOfMeasureCommand result = mapper.entityToCommand(uom);
        assertEquals(result.getId(), uomCommand.getId());
        assertEquals(result.getDescription(), uomCommand.getDescription());
    }

    @Test
    void commandToEntity() {
        UnitOfMeasure result = mapper.commandToEntity(uomCommand);
        assertEquals(result.getId(), uom.getId());
        assertEquals(result.getDescription(), uom.getDescription());
    }

    @Test
    void nullCommand() {
        UnitOfMeasure result = mapper.commandToEntity(null);
        assertNull(result);
    }

    @Test
    void nullEntity() {
        UnitOfMeasureCommand result = mapper.entityToCommand(null);
        assertNull(result);
    }
}