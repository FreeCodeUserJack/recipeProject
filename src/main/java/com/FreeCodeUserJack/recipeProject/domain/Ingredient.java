package com.FreeCodeUserJack.recipeProject.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER) // unidirectional Ingredient to UnitMeasure, no cascade
    private UnitMeasure unit;

    @ManyToOne
    private Recipe recipe;

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitMeasure uom) {
        this.amount = amount;
        this.unit = uom;
        this.description = description;
    }

    public Ingredient(String description, BigDecimal amount, UnitMeasure uom, Recipe recipe) {
        this.amount = amount;
        this.unit = uom;
        this.recipe = recipe;
        this.description = description;
    }

}
