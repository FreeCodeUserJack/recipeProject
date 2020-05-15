package com.FreeCodeUserJack.recipeProject.converters;

import com.FreeCodeUserJack.recipeProject.commands.UnitMeasureCommand;
import com.FreeCodeUserJack.recipeProject.domain.UnitMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitMeasureToUnitMeasureCommandTest {

    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = Long.valueOf(1L);

    UnitMeasureToUnitMeasureCommand converter;

    @Before
    public void setUp() {
        converter = new UnitMeasureToUnitMeasureCommand();
    }

    @Test
    public void testNullParamter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new UnitMeasure()));
    }

    @Test
    public void convert() throws Exception {
        //given
        UnitMeasure unit = new UnitMeasure();
        unit.setId(LONG_VALUE);
        unit.setDescription(DESCRIPTION);

        //when
        UnitMeasureCommand com = converter.convert(unit);

        //then
        assertNotNull(com);
        assertEquals(LONG_VALUE, com.getId());
        assertEquals(DESCRIPTION, com.getDescription());

    }
}