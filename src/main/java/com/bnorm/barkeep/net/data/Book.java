package com.bnorm.barkeep.net.data;

import javax.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Book implements HasId {

    // @formatter:off
    public static Book create(String title, String description) { return create(null, null, title, description); }
    public static Book create(Long id, String type, String title, String description) { return new AutoValue_Book(id, type, title, description); }
    public static JsonAdapter<Book> jsonAdapter(Moshi moshi) { return new AutoValue_Book.MoshiJsonAdapter(moshi); }

    @Nullable public abstract String getType();
    public abstract String getTitle();
    public abstract String getDescription();

    public abstract Book withType(String type);
    public abstract Book withTitle(String title);
    public abstract Book withDescription(String description);
}
