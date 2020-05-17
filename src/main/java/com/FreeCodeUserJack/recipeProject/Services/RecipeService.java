package com.FreeCodeUserJack.recipeProject.Services;

import com.FreeCodeUserJack.recipeProject.commands.RecipeCommand;
import com.FreeCodeUserJack.recipeProject.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command); // take recipe from web controller to save to DB
    RecipeCommand findCommandById(Long id);
    void deleteCommandById(Long id);
}
