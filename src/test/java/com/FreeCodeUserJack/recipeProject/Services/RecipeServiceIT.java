package com.FreeCodeUserJack.recipeProject.Services;

import com.FreeCodeUserJack.recipeProject.commands.RecipeCommand;
import com.FreeCodeUserJack.recipeProject.converters.RecipeCommandToRecipe;
import com.FreeCodeUserJack.recipeProject.converters.RecipeToRecipeCommand;
import com.FreeCodeUserJack.recipeProject.domain.Recipe;
import com.FreeCodeUserJack.recipeProject.repositories.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class) // links up Spring Boot context with JUnit
@SpringBootTest // no mocking involved, for integration tests
public class RecipeServiceIT {
    public static final String DESC = "New Description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Transactional
    @Test
    public void testSaveOfDescription() {
        // given
        Iterable<Recipe> recipes = recipeService.getRecipes();
        Recipe recipe = recipes.iterator().next();
        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);

        // when
        recipeCommand.setDescription(DESC);
        RecipeCommand returnCommand = recipeService.saveRecipeCommand(recipeCommand);

        // then
        assertEquals(DESC, returnCommand.getDescription());
        assertEquals(recipe.getId(), recipeCommand.getId());
        assertEquals(recipe.getCategories().size(), returnCommand.getCategories().size());
        assertEquals(recipe.getIngredients().size(), returnCommand.getIngredients().size());
    }
}
