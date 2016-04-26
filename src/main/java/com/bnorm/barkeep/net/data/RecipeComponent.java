package com.bnorm.barkeep.net.data;

import javax.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;

@AutoValue
public abstract class RecipeComponent {

    // @formatter:off
    public static RecipeComponent create(Ingredient ingredient, double min) { return new AutoValue_RecipeComponent(-1, ingredient, min, null, -1, -1); }
    public static JsonAdapter.Factory typeAdapterFactory() { return AutoValue_RecipeComponent.typeAdapterFactory(); }

    public abstract long getId();
    public abstract Ingredient getIngredient();
    public abstract double getMin();
    @Nullable public abstract Double getMax();
    public abstract int getComponentNum();
    public abstract int getOrder();

    public RecipeComponent withIngredient(Ingredient ingredient) { return new AutoValue_RecipeComponent(getId(), ingredient, getMin(), getMax(), getComponentNum(), getOrder()); }
    public RecipeComponent withMin(double min) { return new AutoValue_RecipeComponent(getId(), getIngredient(), min, getMax(), getComponentNum(), getOrder()); }
    public RecipeComponent withMax(Double max) { return new AutoValue_RecipeComponent(getId(), getIngredient(), getMin(), max, getComponentNum(), getOrder()); }
    public RecipeComponent withComponentNum(int componentNum) { return new AutoValue_RecipeComponent(getId(), getIngredient(), getMin(), getMax(), componentNum, getOrder()); }
    public RecipeComponent withOrder(int order) { return new AutoValue_RecipeComponent(getId(), getIngredient(), getMin(), getMax(), getComponentNum(), order); }
}
