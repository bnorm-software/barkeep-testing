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
import static com.bnorm.barkeep.net.data.Sources.passwords;
import static com.bnorm.barkeep.net.data.Sources.usernames;
import static com.bnorm.barkeep.net.data.Sources.users;
import static com.bnorm.qt.Consumers.throwing;
import static org.assertj.core.api.Assertions.assertThat;
import static org.quicktheories.quicktheories.QuickTheory.qt;
import static org.quicktheories.quicktheories.generators.SourceDSL.strings;

@Category(UnitTest.class)
public class UserTest {

    private static final String EXPECTED = "user.expected.json";
    private static final String INPUT = "user.input.json";

    @Rule
    public final ResourcePathsRule paths = new ResourcePathsRule();

    @Test
    public void create() throws Exception {
        qt().forAll(usernames(), passwords()) //
            .asWithPrecursor(User::create) //
            .checkAssert((username, password, user) -> {
                assertThat(user).hasUsername(username) //
                                .hasPassword(password);
            });
    }

    @Test
    @ResourcePath(EXPECTED)
    @ResourcePath(INPUT)
    public void typeAdapterFactory() throws Exception {
        JsonAdapter<User> adapter = User.jsonAdapter(new Moshi.Builder().build());

        String json = adapter.toJson(User.create("username", "password"));
        assertThat(json).isEqualTo(paths.string(EXPECTED));

        User user = adapter.fromJson(paths.string(INPUT));
        assertThat(user).hasUsername("username") //
                        .hasPassword("password");

        // ascii is the largest set that passes
        qt().forAll(users(strings().ascii().ofLengthBetween(0, 20))) //
            .checkAssert(throwing(here -> {
                String there = adapter.toJson(here);
                User back = adapter.fromJson(there);
                assertThat(here).isEqualTo(back);
            }));
    }

    @Test
    public void withUsername() throws Exception {
        User user = User.create("username", "password");
        assertThat(user).hasUsername("username");

        qt().forAll(usernames()) //
            .asWithPrecursor(user::withUsername) //
            .checkAssert((newUsername, newUser) -> {
                assertThat(newUser).hasUsername(newUsername) //
                                   .hasPassword("password");
            });
    }

    @Test
    public void withPassword() throws Exception {
        User user = User.create("username", "password");
        assertThat(user).hasPassword("password");

        qt().forAll(passwords()) //
            .asWithPrecursor(user::withPassword) //
            .checkAssert((newPassword, newUser) -> {
                assertThat(newUser).hasUsername("username") //
                                   .hasPassword(newPassword);
            });
    }
}
