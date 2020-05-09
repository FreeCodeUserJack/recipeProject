package com.FreeCodeUserJack.recipeProject.repositories;

import com.FreeCodeUserJack.recipeProject.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
