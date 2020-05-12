package com.FreeCodeUserJack.recipeProject.Services;

import com.FreeCodeUserJack.recipeProject.domain.Recipe;
import com.FreeCodeUserJack.recipeProject.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    // service has this dependency as well
    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this); // initializes objects annotated with Mockito annotations for given testClass
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipes() {
        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();
        recipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesData); // when findAll() is called, return recipesData

        Set<Recipe> recipes = recipeService.getRecipes(); // getRecipes() calls findAll() which returns recipesData

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll(); // findAll() is called only 1
    }
}