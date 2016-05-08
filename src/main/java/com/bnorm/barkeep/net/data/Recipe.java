package com.bnorm.barkeep.net.data;

import java.util.List;

import javax.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Recipe {

    // @formatter:off
    public static Recipe create(String title, String description) { return new AutoValue_Recipe(-1, title, description, null, null, null, ImmutableList.of()); }
    public static JsonAdapter<Recipe> jsonAdapter(Moshi moshi) { return new AutoValue_Recipe.MoshiJsonAdapter(moshi); }

    public abstract long getId();
    public abstract String getTitle();
    public abstract String getDescription();
    @Nullable public abstract String getImage();
    @Nullable public abstract String getInstructions();
    @Nullable public abstract String getSource();
    public abstract List<RecipeComponent> getComponents();

    public abstract Recipe withTitle(String title);
    public abstract Recipe withDescription(String description);
    public abstract Recipe withImage(String image);
    public abstract Recipe withInstructions(String instructions);
    public abstract Recipe withSource(String source);
    public abstract Recipe withComponents(List<RecipeComponent> components);
    public final Recipe withComponent(RecipeComponent component) { return withComponents(ImmutableList.<RecipeComponent>builder().addAll(getComponents()).add(component).build()); }
}
