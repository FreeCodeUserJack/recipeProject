package com.FreeCodeUserJack.recipeProject.converters;

import com.FreeCodeUserJack.recipeProject.commands.UnitMeasureCommand;
import com.FreeCodeUserJack.recipeProject.domain.UnitMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitMeasureCommandToUnitMeasure implements Converter<UnitMeasureCommand, UnitMeasure> {
    @Synchronized
    @Nullable
    @Override
    public UnitMeasure convert(UnitMeasureCommand unitMeasureCommand) {
        if (unitMeasureCommand == null) {
            return null;
        }
        final UnitMeasure unitMeasure = new UnitMeasure();
        unitMeasure.setId(unitMeasureCommand.getId());
        unitMeasure.setDescription(unitMeasureCommand.getDescription());
        return unitMeasure;
    }
}
