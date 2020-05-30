package com.FreeCodeUserJack.recipeProject.bootstrap;

import com.FreeCodeUserJack.recipeProject.domain.Category;
import com.FreeCodeUserJack.recipeProject.domain.UnitMeasure;
import com.FreeCodeUserJack.recipeProject.repositories.CategoryRepository;
import com.FreeCodeUserJack.recipeProject.repositories.UnitMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile({"dev", "prod"})
public class BootstrapMySQL implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final UnitMeasureRepository unitMeasureRepository;

    public BootstrapMySQL(CategoryRepository categoryRepository, UnitMeasureRepository unitMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitMeasureRepository = unitMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        if (categoryRepository.count() == 0L){
            log.debug("Loading Categories");
            loadCategories();
        }

        if (unitMeasureRepository.count() == 0L){
            log.debug("Loading UOMs");
            loadUom();
        }
    }

    private void loadCategories(){
        Category cat1 = new Category();
        cat1.setDescription("American");
        categoryRepository.save(cat1);

        Category cat2 = new Category();
        cat2.setDescription("Italian");
        categoryRepository.save(cat2);

        Category cat3 = new Category();
        cat3.setDescription("Mexican");
        categoryRepository.save(cat3);

        Category cat4 = new Category();
        cat4.setDescription("Fast Food");
        categoryRepository.save(cat4);
    }

    private void loadUom(){
        UnitMeasure uom1 = new UnitMeasure();
        uom1.setDescription("Teaspoon");
        unitMeasureRepository.save(uom1);

        UnitMeasure uom2 = new UnitMeasure();
        uom2.setDescription("Tablespoon");
        unitMeasureRepository.save(uom2);

        UnitMeasure uom3 = new UnitMeasure();
        uom3.setDescription("Cup");
        unitMeasureRepository.save(uom3);

        UnitMeasure uom4 = new UnitMeasure();
        uom4.setDescription("Pinch");
        unitMeasureRepository.save(uom4);

        UnitMeasure uom5 = new UnitMeasure();
        uom5.setDescription("Ounce");
        unitMeasureRepository.save(uom5);

        UnitMeasure uom6 = new UnitMeasure();
        uom6.setDescription("Each");
        unitMeasureRepository.save(uom6);

        UnitMeasure uom7 = new UnitMeasure();
        uom7.setDescription("Pint");
        unitMeasureRepository.save(uom7);

        UnitMeasure uom8 = new UnitMeasure();
        uom8.setDescription("Dash");
        unitMeasureRepository.save(uom8);
    }
}
