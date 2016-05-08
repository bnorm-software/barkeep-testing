package com.bnorm.barkeep.net.data;

import javax.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Book {

    // @formatter:off
    public static Book create(String title, String description) { return new AutoValue_Book(-1, null, title, description); }
    public static JsonAdapter<Book> jsonAdapter(Moshi moshi) { return new AutoValue_Book.MoshiJsonAdapter(moshi); }

    public abstract long getId();
    @Nullable public abstract String getType();
    public abstract String getTitle();
    public abstract String getDescription();

    public abstract Book withType(String type);
    public abstract Book withTitle(String title);
    public abstract Book withDescription(String description);
}
