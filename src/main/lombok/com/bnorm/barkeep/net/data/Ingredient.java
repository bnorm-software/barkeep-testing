package com.bnorm.barkeep.net.data;

import lombok.Data;

@Data
public class Ingredient {

    private long id;
    private final String title;
    private final Ingredient base;
}
