package com.bnorm.barkeep.net.data;

import javax.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;

@AutoValue
public abstract class Book {

    // @formatter:off
    public static Book create(String title, String description) { return new AutoValue_Book(-1, null, title, description); }
    public static JsonAdapter.Factory typeAdapterFactory() { return AutoValue_Book.typeAdapterFactory(); }

    public abstract long getId();
    @Nullable public abstract String getType();
    public abstract String getTitle();
    public abstract String getDescription();

    public Book withType(String type) { return new AutoValue_Book(getId(), type, getTitle(), getDescription()); }
    public Book withTitle(String title) { return new AutoValue_Book(getId(), getType(), title, getDescription()); }
    public Book withDescription(String description) { return new AutoValue_Book(getId(), getType(), getTitle(), description); }
}
