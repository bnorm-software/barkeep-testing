package com.bnorm.barkeep.net;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.bnorm.IntegrationTest;
import com.bnorm.barkeep.net.data.User;

import retrofit2.Response;

import static org.assertj.core.api.Assertions.assertThat;

@Category(IntegrationTest.class)
public class BarkeepService_AuthenticationTest extends BarkeepServiceBaseTest {

    @Test
    public void login_success() throws Exception {
        // given

        // when
        Response<Void> response = service.login(TEST_USER).execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
    }

    @Test
    public void login_failure_badUsername() throws Exception {
        // given

        // when
        Response<Void> response = service.login(User.create("joewrong", "nohomohug")).execute();

        // then
        assertThat(response.isSuccessful()).isFalse();
        assertThat(response.raw().code()).isEqualTo(CODE_UNAUTHORIZED);
    }

    @Test
    public void login_failure_badPassword() throws Exception {
        // given

        // when
        Response<Void> response = service.login(User.create("joe", "homohug")).execute();

        // then
        assertThat(response.isSuccessful()).isFalse();
        assertThat(response.raw().code()).isEqualTo(CODE_UNAUTHORIZED);
    }

    @Test
    public void login_failure_badEverything() throws Exception {
        // given

        // when
        Response<Void> response = service.login(User.create("joewrong", "homohug")).execute();

        // then
        assertThat(response.isSuccessful()).isFalse();
        assertThat(response.raw().code()).isEqualTo(CODE_UNAUTHORIZED);
    }

    @Test
    public void logout_success() throws Exception {
        // given
        service.login(TEST_USER).execute();

        // when
        Response<Void> response = service.logout().execute();

        // then
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.raw().code()).isEqualTo(CODE_SUCCESS);
    }

    @Test
    public void logout_failure_notLoggedIn() throws Exception {
        // given

        // when
        Response<Void> response = service.logout().execute();

        // then
        assertThat(response.isSuccessful()).isFalse();
        assertThat(response.raw().code()).isEqualTo(CODE_UNAUTHORIZED);
    }
}
