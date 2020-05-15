package com.FreeCodeUserJack.recipeProject.converters;

import com.FreeCodeUserJack.recipeProject.commands.UnitMeasureCommand;
import com.FreeCodeUserJack.recipeProject.domain.UnitMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitMeasureCommandToUnitMeasureTest {

    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = Long.valueOf(1L);

    UnitMeasureCommandToUnitMeasure converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitMeasureCommandToUnitMeasure();

    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new UnitMeasureCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        UnitMeasureCommand command = new UnitMeasureCommand();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        //when
        UnitMeasure uom = converter.convert(command);

        //then
        assertNotNull(uom);
        assertEquals(LONG_VALUE, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());

    }

}