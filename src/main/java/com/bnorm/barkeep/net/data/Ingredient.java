package com.bnorm.barkeep.net.data;

import javax.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Ingredient implements HasId {

    // @formatter:off
    public static Ingredient create(String title) { return create(-1, title, null); }
    public static Ingredient create(String title, Ingredient ingredient) { return create(-1, title, ingredient); }
    public static Ingredient create(long id, String title, Ingredient ingredient) { return new AutoValue_Ingredient(id, title, ingredient); }
    public static JsonAdapter<Ingredient> jsonAdapter(Moshi moshi) { return new AutoValue_Ingredient.MoshiJsonAdapter(moshi); }

    public abstract String getTitle();
    @Nullable public abstract Ingredient getBase();

    public abstract Ingredient withTitle(String title);
    public abstract Ingredient withBase(Ingredient base);
}
