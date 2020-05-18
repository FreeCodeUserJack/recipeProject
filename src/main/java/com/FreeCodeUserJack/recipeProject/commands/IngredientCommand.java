package com.FreeCodeUserJack.recipeProject.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
    private Long id;
    private Long recipeId; // don't need entire Recipe object
    private String description;
    private BigDecimal amount;
    private UnitMeasureCommand unitOfMeasure;
}
