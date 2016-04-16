package com.bnorm.barkeep.net.data;

import java.util.List;

import javax.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import com.squareup.moshi.JsonAdapter;

@AutoValue
public abstract class Recipe {

    // @formatter:off
    public static Recipe create(String title, String description) { return new AutoValue_Recipe(-1, title, description, null, null, null, ImmutableList.of()); }
    public static JsonAdapter.Factory typeAdapterFactory() { return AutoValue_Recipe.typeAdapterFactory(); }

    public abstract long id();
    public abstract String title();
    public abstract String description();
    @Nullable public abstract String image();
    @Nullable public abstract String instructions();
    @Nullable public abstract String source();
    public abstract List<RecipeComponent> components();

    public abstract Recipe withId(long id);
    public abstract Recipe withTitle(String title);
    public abstract Recipe withDescription(String description);
    public abstract Recipe withImage(String image);
    public abstract Recipe withInstructions(String instructions);
    public abstract Recipe withSource(String source);
    public abstract Recipe withComponents(List<RecipeComponent> components);
}
