package com.bnorm.barkeep.net.data;

import javax.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;

@AutoValue
public abstract class Book {

    // @formatter:off
    public static Book create(String title, String description) { return new AutoValue_Book(-1, null, title, description); }
    public static JsonAdapter.Factory typeAdapterFactory() { return AutoValue_Book.typeAdapterFactory(); }

    public abstract long id();
    @Nullable public abstract String type();
    public abstract String title();
    public abstract String description();

    public abstract Book withId(long id);
    public abstract Book withType(String type);
    public abstract Book withTitle(String title);
    public abstract Book withDescription(String description);
}
