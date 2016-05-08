package com.bnorm.barkeep.net.data;

import org.junit.Test;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import static com.bnorm.barkeep.net.data.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    public void create() throws Exception {
        User user = User.create("username", "password");
        assertThat(user).hasUsername("username").hasPassword("password");
    }

    @Test
    public void typeAdapterFactory() throws Exception {
        JsonAdapter<User> adapter = User.jsonAdapter(new Moshi.Builder().build());

        String json = adapter.toJson(User.create("username", "password"));
        assertThat(json).isEqualTo("{\"username\":\"username\",\"password\":\"password\"}");

        User user = adapter.fromJson("{\"username\":\"username\",\"password\":\"password\"}");
        assertThat(user).hasUsername("username").hasPassword("password");
    }

    @Test
    public void withUsername() throws Exception {
        User user = User.create("username", "password");
        assertThat(user).hasUsername("username");

        user = user.withUsername("username1");
        assertThat(user).hasUsername("username1");
    }

    @Test
    public void withPassword() throws Exception {
        User user = User.create("username", "password");
        assertThat(user).hasPassword("password");

        user = user.withPassword("password1");
        assertThat(user).hasPassword("password1");
    }
}