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

    public abstract long getId();
    public abstract String getTitle();
    public abstract String getDescription();
    @Nullable public abstract String getImage();
    @Nullable public abstract String getInstructions();
    @Nullable public abstract String getSource();
    public abstract List<RecipeComponent> getComponents();

    public Recipe withTitle(String title) { return new AutoValue_Recipe(getId(), title, getDescription(), getImage(), getInstructions(), getSource(), getComponents()); }
    public Recipe withDescription(String description) { return new AutoValue_Recipe(getId(), getTitle(), description, getImage(), getInstructions(), getSource(), getComponents()); }
    public Recipe withImage(String image) { return new AutoValue_Recipe(getId(), getTitle(), getDescription(), image, getInstructions(), getSource(), getComponents()); }
    public Recipe withInstructions(String instructions) { return new AutoValue_Recipe(getId(), getTitle(), getDescription(), getImage(), instructions, getSource(), getComponents()); }
    public Recipe withSource(String source) { return new AutoValue_Recipe(getId(), getTitle(), getDescription(), getImage(), getInstructions(), source, getComponents()); }
    public Recipe withComponents(List<RecipeComponent> components) { return new AutoValue_Recipe(getId(), getTitle(), getDescription(), getImage(), getInstructions(), getSource(), components); }
    public final Recipe withComponent(RecipeComponent component) { return withComponents(ImmutableList.<RecipeComponent>builder().addAll(getComponents()).add(component).build()); }
}
