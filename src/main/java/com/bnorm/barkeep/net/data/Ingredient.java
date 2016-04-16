package com.bnorm.barkeep.net.data;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;

@AutoValue
public abstract class Ingredient {

    // @formatter:off
    public static Ingredient create(String title, Ingredient ingredient) { return new AutoValue_Ingredient(-1, title, ingredient); }
    public static JsonAdapter.Factory typeAdapterFactory() { return AutoValue_Ingredient.typeAdapterFactory(); }

    public abstract long id();
    public abstract String title();
    public abstract Ingredient base();

    public abstract Ingredient withId(long id);
    public abstract Ingredient withTitle(String title);
    public abstract Ingredient withBase(Ingredient base);
}
