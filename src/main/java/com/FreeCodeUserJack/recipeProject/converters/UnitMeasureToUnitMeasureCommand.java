package com.FreeCodeUserJack.recipeProject.converters;

import com.FreeCodeUserJack.recipeProject.commands.UnitMeasureCommand;
import com.FreeCodeUserJack.recipeProject.domain.UnitMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitMeasureToUnitMeasureCommand implements Converter<UnitMeasure, UnitMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitMeasureCommand convert(UnitMeasure unitMeasure) {
        if (unitMeasure == null) {
            return null;
        }
        final UnitMeasureCommand com = new UnitMeasureCommand();
        com.setId(unitMeasure.getId());
        com.setDescription(unitMeasure.getDescription());
        return com;
    }
}
