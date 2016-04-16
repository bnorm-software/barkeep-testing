package com.bnorm.barkeep.net.data;

import javax.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;

@AutoValue
public abstract class RecipeComponent {

    // @formatter:off
    public static RecipeComponent create(Ingredient ingredient, double min) { return new AutoValue_RecipeComponent(ingredient, min, null, -1, -1); }
    public static JsonAdapter.Factory typeAdapterFactory() { return AutoValue_RecipeComponent.typeAdapterFactory(); }

    public abstract Ingredient ingredient();
    public abstract double min();
    @Nullable public abstract Double max();
    public abstract int componentNum();
    public abstract int order();

    public abstract RecipeComponent withIngredient(Ingredient ingredient);
    public abstract RecipeComponent withMin(double min);
    public abstract RecipeComponent withMax(Double max);
    public abstract RecipeComponent withComponentNum(int componentNum);
    public abstract RecipeComponent withOrder(int order);
}
