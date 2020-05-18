package com.FreeCodeUserJack.recipeProject.Services;

import com.FreeCodeUserJack.recipeProject.commands.IngredientCommand;
import com.FreeCodeUserJack.recipeProject.converters.IngredientToIngredientCommand;
import com.FreeCodeUserJack.recipeProject.domain.Recipe;
import com.FreeCodeUserJack.recipeProject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()) {
            log.error("recipe id not found: " + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ing -> ing.getId().equals(ingredientId))
                .map(ing -> ingredientToIngredientCommand.convert(ing))
                .findFirst();

        if (!ingredientCommandOptional.isPresent()) {
            log.error("ingredient id not found: " + ingredientId);
        }

        return ingredientCommandOptional.get();
    }
}
