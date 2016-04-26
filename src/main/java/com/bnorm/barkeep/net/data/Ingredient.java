package com.bnorm.barkeep.net.data;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;

@AutoValue
public abstract class Ingredient {

    // @formatter:off
    public static Ingredient create(String title, Ingredient ingredient) { return new AutoValue_Ingredient(-1, title, ingredient); }
    public static JsonAdapter.Factory typeAdapterFactory() { return AutoValue_Ingredient.typeAdapterFactory(); }

    public abstract long getId();
    public abstract String getTitle();
    public abstract Ingredient getBase();

    public Ingredient withTitle(String title) { return new AutoValue_Ingredient(getId(), title, getBase()); }
    public Ingredient withBase(Ingredient base) { return new AutoValue_Ingredient(getId(), getTitle(), base); }
}
