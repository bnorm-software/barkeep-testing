package com.bnorm.barkeep.net.data;

import javax.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;

@AutoValue
public abstract class Bar {

    // @formatter:off
    public static Bar create(String title, String description) { return new AutoValue_Bar(-1, null, title, description); }
    public static JsonAdapter.Factory typeAdapterFactory() { return AutoValue_Bar.typeAdapterFactory(); }

    public abstract long id();
    @Nullable public abstract String type();
    public abstract String title();
    public abstract String description();

    public abstract Bar withId(long id);
    public abstract Bar withType(String type);
    public abstract Bar withTitle(String title);
    public abstract Bar withDescription(String description);
}
