package com.bnorm.barkeep.net.data;

import javax.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class RecipeComponent implements HasId {

    // @formatter:off
    public static RecipeComponent create(Ingredient ingredient, double min) { return new AutoValue_RecipeComponent(-1, ingredient, min, null, -1, -1); }
    public static JsonAdapter<RecipeComponent> jsonAdapter(Moshi moshi) { return new AutoValue_RecipeComponent.MoshiJsonAdapter(moshi); }

    public abstract Ingredient getIngredient();
    public abstract double getMin();
    @Nullable public abstract Double getMax();
    public abstract int getComponentNum();
    public abstract int getOrder();

    public abstract RecipeComponent withIngredient(Ingredient ingredient);
    public abstract RecipeComponent withMin(double min);
    public abstract RecipeComponent withMax(Double max);
    public abstract RecipeComponent withComponentNum(int componentNum);
    public abstract RecipeComponent withOrder(int order);
}
