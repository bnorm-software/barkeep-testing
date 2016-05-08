package com.bnorm.barkeep.net.data;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class User {

    // @formatter:off
    public static User create(String username, String password) { return new AutoValue_User(username, password); }
    public static JsonAdapter<User> jsonAdapter(Moshi moshi) { return new AutoValue_User.MoshiJsonAdapter(moshi); }

    public abstract String getUsername();
    public abstract String getPassword();

    public abstract User withUsername(String username);
    public abstract User withPassword(String password);
}
