package com.bnorm.barkeep.net.data;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;

@AutoValue
public abstract class User {

    // @formatter:off
    public static User create(String username, String password) { return new AutoValue_User(username, password); }
    public static JsonAdapter.Factory typeAdapterFactory() { return AutoValue_User.typeAdapterFactory(); }

    public abstract String username();
    public abstract String password();

    public abstract User withUsername(String username);
    public abstract User withPassword(String password);
}
