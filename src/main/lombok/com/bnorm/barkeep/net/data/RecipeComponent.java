package com.bnorm.barkeep.net.data;

import lombok.Data;

@Data
public class RecipeComponent {

    private final Ingredient ingredient;
    private final double min;
    private Double max;
    private int componentNum;
    private int order;
}
