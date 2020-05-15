package com.FreeCodeUserJack.recipeProject.converters;

import com.FreeCodeUserJack.recipeProject.commands.CategoryCommand;
import com.FreeCodeUserJack.recipeProject.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    public static final Long ID = Long.valueOf(1L);
    public static final String DESC = "description";
    CategoryToCategoryCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObejct() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convert() {
        // given
        Category cat = new Category();
        cat.setId(ID);
        cat.setDescription(DESC);

        // when
        CategoryCommand com = converter.convert(cat);

        // then
        assertEquals(ID, com.getId());
        assertEquals(DESC, com.getDescription());
    }
}