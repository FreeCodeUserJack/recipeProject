package com.FreeCodeUserJack.recipeProject.converters;

import com.FreeCodeUserJack.recipeProject.commands.CategoryCommand;
import com.FreeCodeUserJack.recipeProject.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    public static final Long ID = Long.valueOf(1L);
    public static final String DESC = "description";

    CategoryCommandToCategory converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() {
        // given
        CategoryCommand com = new CategoryCommand();
        com.setId(ID);
        com.setDescription(DESC);

        // when
        Category cat = converter.convert(com);

        // then
        assertEquals(ID, cat.getId());
        assertEquals(DESC, cat.getDescription());
    }
}