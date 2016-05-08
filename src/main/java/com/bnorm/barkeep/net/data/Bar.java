package com.bnorm.barkeep.net.data;

import javax.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Bar {

    // @formatter:off
    public static Bar create(String title, String description) { return new AutoValue_Bar(-1, null, title, description); }
    public static JsonAdapter<Bar> jsonAdapter(Moshi moshi) { return new AutoValue_Bar.MoshiJsonAdapter(moshi); }

    public abstract long getId();
    @Nullable public abstract String getType();
    public abstract String getTitle();
    public abstract String getDescription();

    public abstract Bar withType(String type);
    public abstract Bar withTitle(String title);
    public abstract Bar withDescription(String description);
}
