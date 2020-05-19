package com.FreeCodeUserJack.recipeProject.Services;

import com.FreeCodeUserJack.recipeProject.commands.UnitMeasureCommand;

import java.util.Set;

public interface UnitMeasureService {
    Set<UnitMeasureCommand> listAllUnitMeasures();
}
