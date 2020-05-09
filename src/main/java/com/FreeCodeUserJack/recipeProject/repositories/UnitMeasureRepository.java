package com.FreeCodeUserJack.recipeProject.repositories;

import com.FreeCodeUserJack.recipeProject.domain.UnitMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitMeasureRepository extends CrudRepository<UnitMeasure, Long> {
    Optional<UnitMeasure> findByDescription(String description);
}
