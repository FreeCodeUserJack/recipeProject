package com.FreeCodeUserJack.recipeProject.repositories;

import com.FreeCodeUserJack.recipeProject.domain.UnitMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

// IT for Integration Test
@RunWith(SpringRunner.class) // could do SpringBootTest, but we use below JPA
@DataJpaTest
public class UnitMeasureRepositoryIT {

    @Autowired
    UnitMeasureRepository unitMeasureRepository;

    @Before
    public void setUp() throws Exception {
    }

    // this test DOES run against the underlying database, I think the data-h2.sql is also being run

    @Test
    public void findByDescription() {
        Optional<UnitMeasure> unitMeasureOptional = unitMeasureRepository.findByDescription("Teaspoon");

        assertEquals("Teaspoon", unitMeasureOptional.get().getDescription());
    }

    // 2 tests BUT only ONE loading of the JpaData context
        // to reload context, then use @DirtiesContext if prev test messes up the context and u want to reset
    @Test
    public void findByDescriptionCup() {
        Optional<UnitMeasure> unitMeasureOptional = unitMeasureRepository.findByDescription("Cup");

        assertEquals("Cup", unitMeasureOptional.get().getDescription());
    }
}