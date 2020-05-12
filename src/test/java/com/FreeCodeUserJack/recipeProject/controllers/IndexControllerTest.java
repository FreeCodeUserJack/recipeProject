package com.FreeCodeUserJack.recipeProject.controllers;

import com.FreeCodeUserJack.recipeProject.Services.RecipeService;
import com.FreeCodeUserJack.recipeProject.domain.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this); // init all props with @Mock
        indexController = new IndexController(recipeService);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build(); // fluent api

        mockMvc.perform(get("/")).andExpect(status().isOk()) // get back 200~ status code
            .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {
        //given
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());

        // if you don't declare 2 diff recipe then "new Recipe()" is actually equal to each other
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipes.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        // verify getIndexPage() will return a string that equals "index"
        String viewName = indexController.getIndexPage(model);
        assertEquals("index", viewName);

        // verify that model.addAttribute() is called only once -> anySet() changed to captor
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());

        // capture() captures the value put into above method call
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());

        // verify recipeService.getRecipes() is called only once
        verify(recipeService, times(1)).getRecipes();
    }
}