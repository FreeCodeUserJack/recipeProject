package com.FreeCodeUserJack.recipeProject.Services;

import com.FreeCodeUserJack.recipeProject.commands.UnitMeasureCommand;
import com.FreeCodeUserJack.recipeProject.converters.UnitMeasureToUnitMeasureCommand;
import com.FreeCodeUserJack.recipeProject.repositories.UnitMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitMeasureServiceImpl implements UnitMeasureService {
    private final UnitMeasureRepository measureRepository;
    private final UnitMeasureToUnitMeasureCommand unitMeasureToUnitMeasureCommand;

    public UnitMeasureServiceImpl(UnitMeasureRepository measureRepository, UnitMeasureToUnitMeasureCommand unitMeasureToUnitMeasureCommand) {
        this.measureRepository = measureRepository;
        this.unitMeasureToUnitMeasureCommand = unitMeasureToUnitMeasureCommand;
    }

    @Override
    public Set<UnitMeasureCommand> listAllUnitMeasures() {
        return StreamSupport.stream(measureRepository.findAll().spliterator(), false)
                .map(unitMeasureToUnitMeasureCommand::convert)
                .collect(Collectors.toSet());
    }
}
