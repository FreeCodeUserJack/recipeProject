package com.FreeCodeUserJack.recipeProject.Services;

import com.FreeCodeUserJack.recipeProject.commands.IngredientCommand;
import com.FreeCodeUserJack.recipeProject.converters.IngredientCommandToIngredient;
import com.FreeCodeUserJack.recipeProject.converters.IngredientToIngredientCommand;
import com.FreeCodeUserJack.recipeProject.domain.Ingredient;
import com.FreeCodeUserJack.recipeProject.domain.Recipe;
import com.FreeCodeUserJack.recipeProject.repositories.RecipeRepository;
import com.FreeCodeUserJack.recipeProject.repositories.UnitMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final UnitMeasureRepository unitMeasureRepository;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient, UnitMeasureRepository unitMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.unitMeasureRepository = unitMeasureRepository;
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

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        // debug
        System.out.println("Inside IngredientServiceImpl!: " + command.getUnitOfMeasure());

        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if (!recipeOptional.isPresent()) {
            // todo toss error if not found -> right now is happy path, if not found, need to address
            log.error("Ingredient with id not found: " + command.getId());
            return new IngredientCommand();
        }

        Recipe recipe = recipeOptional.get();

        Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
                .filter(ing -> ing.getId().equals(command.getId()))
                .findFirst();

        if (ingredientOptional.isPresent()) {
            Ingredient ingredientFound = ingredientOptional.get();
            ingredientFound.setDescription(command.getDescription());
            ingredientFound.setAmount(command.getAmount());
            ingredientFound.setUnit(unitMeasureRepository.findById(command.getUnitOfMeasure()
                .getId()).orElseThrow(() -> new RuntimeException("UOM Not Found"))); // todo address this
        }
        else {
            // add new Ingredient
            Ingredient ingredient = ingredientCommandToIngredient.convert(command);
            // need to set recipe Object b/c we were using recipe Id in commands but obj in DB
            ingredient.setRecipe(recipe);
            recipe.addIngredient(ingredient);
        }

        Recipe savedRecipe = recipeRepository.save(recipe);

        Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients()
                .stream().filter(ing -> ing.getId().equals(command.getId())).findFirst();

        // check by description, if no ingredient found, try to find again
        if(!savedIngredientOptional.isPresent()) { // what if new ingredient, not in DB? then no id
            // not totally save but best guess
            savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(ing -> ing.getAmount().equals(command.getAmount()))
                    .filter(ing -> ing.getDescription().equals(command.getDescription()))
                    .filter(ing -> ing.getUnit().getId().equals(command.getUnitOfMeasure().getId()))
                    .findFirst();
        }

        // todo check for fail
        return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
    }
}
