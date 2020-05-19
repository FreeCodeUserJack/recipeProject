package com.FreeCodeUserJack.recipeProject.Services;

import com.FreeCodeUserJack.recipeProject.commands.IngredientCommand;
import com.FreeCodeUserJack.recipeProject.converters.IngredientCommandToIngredient;
import com.FreeCodeUserJack.recipeProject.converters.IngredientToIngredientCommand;
import com.FreeCodeUserJack.recipeProject.converters.UnitMeasureCommandToUnitMeasure;
import com.FreeCodeUserJack.recipeProject.converters.UnitMeasureToUnitMeasureCommand;
import com.FreeCodeUserJack.recipeProject.domain.Ingredient;
import com.FreeCodeUserJack.recipeProject.domain.Recipe;
import com.FreeCodeUserJack.recipeProject.repositories.RecipeRepository;
import com.FreeCodeUserJack.recipeProject.repositories.UnitMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    private final IngredientToIngredientCommand converter;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    @Mock
    UnitMeasureRepository unitMeasureRepository;

    @Mock
    RecipeRepository recipeRepository;

    IngredientService ingredientService;

    public IngredientServiceImplTest() {
        // test need to have exactly 1 public zero-argument constructor, so we initialize inside this
        this.converter = new IngredientToIngredientCommand(new UnitMeasureToUnitMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitMeasureCommandToUnitMeasure());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(recipeRepository, converter, ingredientCommandToIngredient, unitMeasureRepository);
    }

    @Test
    public void findByRecipeIdAndIngredientId() {

    }

    @Test
    public void findByRecipeIdAndIngredientIdHappyPath() {
        // given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(1L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        // when
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        // then
        IngredientCommand com = ingredientService.findByRecipeIdAndIngredientId(recipe.getId(), 3L);
        assertEquals(Long.valueOf(1L), com.getRecipeId());
        assertEquals(Long.valueOf(3L), com.getId());

            // have to pass in mock object in to verify method
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testSaveRecipe() {
        // given
        IngredientCommand com = new IngredientCommand();
        com.setId(3L);
        com.setRecipeId(2L);

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(3L);

        // when
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(com);

        //then
        assertEquals(Long.valueOf(3L), savedCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }
}