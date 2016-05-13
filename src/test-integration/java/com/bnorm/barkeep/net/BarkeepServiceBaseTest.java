package com.bnorm.barkeep.net;

import java.io.IOException;
import java.util.Set;

import org.assertj.core.api.Condition;
import org.junit.Before;
import org.junit.experimental.categories.Category;

import com.bnorm.IntegrationTest;
import com.bnorm.barkeep.net.data.HasId;
import com.bnorm.barkeep.net.data.NetDataModule;
import com.bnorm.barkeep.net.data.User;
import com.google.common.collect.ImmutableSet;

import dagger.Component;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Category(IntegrationTest.class)
class BarkeepServiceBaseTest {

    // constants
    static final int CODE_SUCCESS = 200;
    static final int CODE_UNAUTHORIZED = 401;
    static final int CODE_FORBIDDEN = 403;
    static final int CODE_NOT_FOUND = 404;

    static final User TEST_USER = User.create("joe", "nohomohug");

    // conditions
    static final Condition<? super HasId> VALID_ID = new Condition<>(b -> b.getId() != -1, "valid id");

    // test service
    BarkeepService service;

    @Before
    public void setup() throws Exception {
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder();
        urlBuilder.scheme("https");
        urlBuilder.host("barkeep.beefyhost.com");
        urlBuilder.port(8443);
        urlBuilder.addPathSegment("api");
        urlBuilder.addPathSegment("rest");
        urlBuilder.addPathSegment("v1");
        // This last, empty segment adds a trailing '/' which is required for relative paths in the annotations
        urlBuilder.addPathSegment("");

        NetModule netModule = new TestNetModule(urlBuilder.build());
        TestComponent testComponent = DaggerBarkeepServiceBaseTest_TestComponent.builder().netModule(netModule).build();

        service = testComponent.service();

        testComponent.client()
                     .newCall(new Request.Builder().post(RequestBody.create(MediaType.parse("application/json"), ""))
                                                   .url("https://barkeep.beefyhost.com:8443/api/rest/v1/reset")
                                                   .build())
                     .execute();
    }

    @NetScope
    @Component(modules = {NetModule.class, NetDataModule.class})
    interface TestComponent {

        BarkeepService service();

        OkHttpClient client();
    }

    private static class TestNetModule extends NetModule {

        private TestNetModule(HttpUrl httpUrl) {
            super(httpUrl);
        }

        @Override
        Set<Interceptor> provideInterceptors(CacheInterceptor cacheInterceptor) {
            return ImmutableSet.<Interceptor>builder().addAll(super.provideInterceptors(cacheInterceptor))
                                                      .add(new UnitTestInterceptor())
                                                      .build();
        }
    }

    private static class UnitTestInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            builder.addHeader("Unittest", "true");
            return chain.proceed(builder.build());
        }
    }
}
