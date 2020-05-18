package com.FreeCodeUserJack.recipeProject.Services;

import com.FreeCodeUserJack.recipeProject.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
