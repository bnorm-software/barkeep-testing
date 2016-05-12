package com.bnorm.barkeep.net.data;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Ingredient implements HasId {

    // @formatter:off
    public static Ingredient create(String title, Ingredient ingredient) { return new AutoValue_Ingredient(-1, title, ingredient); }
    public static JsonAdapter<Ingredient> jsonAdapter(Moshi moshi) { return new AutoValue_Ingredient.MoshiJsonAdapter(moshi); }

    public abstract String getTitle();
    public abstract Ingredient getBase();

    public abstract Ingredient withTitle(String title);
    public abstract Ingredient withBase(Ingredient base);
}
