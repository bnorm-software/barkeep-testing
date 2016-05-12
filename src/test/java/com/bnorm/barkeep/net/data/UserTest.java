package com.bnorm.barkeep.net.data;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.bnorm.ResourcePath;
import com.bnorm.ResourcePathsRule;
import com.bnorm.UnitTest;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import static com.bnorm.barkeep.net.data.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

@Category(UnitTest.class)
public class UserTest {

    private static final String EXPECTED = "user.expected.json";
    private static final String INPUT = "user.input.json";

    @Rule
    public final ResourcePathsRule paths = new ResourcePathsRule();

    @Test
    public void create() throws Exception {
        User user = User.create("username", "password");
        assertThat(user).hasUsername("username").hasPassword("password");
    }

    @Test
    @ResourcePath(EXPECTED)
    @ResourcePath(INPUT)
    public void typeAdapterFactory() throws Exception {
        JsonAdapter<User> adapter = User.jsonAdapter(new Moshi.Builder().build());

        String json = adapter.toJson(User.create("username", "password"));
        assertThat(json).isEqualTo(paths.string(EXPECTED));

        User user = adapter.fromJson(paths.string(INPUT));
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
