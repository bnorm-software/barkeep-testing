package com.bnorm.barkeep.net.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Recipe {

    private long id;
    private final String title;
    private final String description;
    private String image;
    private String instructions;
    private String source;
    private List<RecipeComponent> components = new ArrayList<>();
}
