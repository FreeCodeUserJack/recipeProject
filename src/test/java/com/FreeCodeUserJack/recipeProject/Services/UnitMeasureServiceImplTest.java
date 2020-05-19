package com.FreeCodeUserJack.recipeProject.Services;

import com.FreeCodeUserJack.recipeProject.commands.UnitMeasureCommand;
import com.FreeCodeUserJack.recipeProject.converters.UnitMeasureToUnitMeasureCommand;
import com.FreeCodeUserJack.recipeProject.domain.UnitMeasure;
import com.FreeCodeUserJack.recipeProject.repositories.UnitMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UnitMeasureServiceImplTest {
    UnitMeasureToUnitMeasureCommand unitMeasureToUnitMeasureCommand;

    @Mock
    UnitMeasureRepository unitMeasureRepository;

    UnitMeasureService unitMeasureService;

    @Before
    public void setUp() throws Exception {
        unitMeasureToUnitMeasureCommand = new UnitMeasureToUnitMeasureCommand();
        MockitoAnnotations.initMocks(this);
        unitMeasureService = new UnitMeasureServiceImpl(unitMeasureRepository, unitMeasureToUnitMeasureCommand);
    }

    @Test
    public void listAllUnitMeasures() {
        // given
        Set<UnitMeasure> units = new HashSet<>();
        UnitMeasure unit = new UnitMeasure();
        unit.setId(1L);
        unit.setDescription("Some desc");

        units.add(unit);

        // when
        when(unitMeasureRepository.findAll()).thenReturn(units);

        Set<UnitMeasureCommand> coms = unitMeasureService.listAllUnitMeasures();

        // then
        assertEquals(1, coms.size());
        verify(unitMeasureRepository, times(1)).findAll();
    }
}